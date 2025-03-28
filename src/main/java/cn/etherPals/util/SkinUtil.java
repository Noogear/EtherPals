package cn.etherPals.util;


import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MINUTES;

public class SkinUtil {

    private static final Pattern BASE64_PATTERN = Pattern.compile("[-A-Za-z0-9+/]{100,}={0,3}");
    private static final Pattern MOJANG_SHA256_APPROX_PATTERN = Pattern.compile("[0-9a-z]{55,70}");
    private static final LoadingCache<String, PlayerProfile> CACHE = CacheBuilder.newBuilder()
            .expireAfterAccess(1, MINUTES)
            .initialCapacity(10)
            .build(new CacheLoader<>() {
                @Override
                public @NotNull PlayerProfile load(@NotNull String key) throws MalformedURLException {
                    Optional<URL> url = Validate.getURL(key);
                    if (url.isPresent()) {
                        return getProfileByURL(url.get());
                    }

                    if (MOJANG_SHA256_APPROX_PATTERN.matcher(key).matches()) {
                        return getProfileByURL("https://textures.minecraft.net/texture/" + key);
                    }

                    if (BASE64_PATTERN.matcher(key).matches()) {
                        return getProfileByBase64(key);
                    }

                    Optional<UUID> uuid = Validate.getUUID(key);
                    return uuid.map(SkinUtil::getProfileByUUID).orElseGet(() -> getProfileByName(key));
                }
            });

    private static @NotNull PlayerProfile getProfileByName(@NotNull String key) {
        Player player = Bukkit.getPlayer(key);
        if (player != null) {
            return player.getPlayerProfile();
        } else {
            return Bukkit.createProfile(key);
        }
    }

    private static @NotNull PlayerProfile getProfileByUUID(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            return player.getPlayerProfile();
        } else {
            return Bukkit.createProfile(uuid);
        }
    }

    private static @NotNull PlayerProfile getProfileByBase64(@NotNull String key) {
        try {
            String decoded = new String(Base64.getDecoder().decode(key), StandardCharsets.UTF_8);
            JsonObject json = new Gson().fromJson(decoded, JsonObject.class);
            String url = json.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
            return getProfileByURL(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull PlayerProfile getProfileByURL(URL url) {
        PlayerProfile newProfile = Bukkit.createProfile(UUID.randomUUID(), "");
        PlayerTextures textures = newProfile.getTextures();
        textures.setSkin(url);
        return newProfile;
    }

    private static @NotNull PlayerProfile getProfileByURL(String url) {
        try {
            return getProfileByURL(new URL(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
