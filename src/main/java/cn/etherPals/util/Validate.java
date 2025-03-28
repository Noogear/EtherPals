package cn.etherPals.util;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

public class Validate {

    public static Optional<URL> getURL(@NotNull String input) {
        try {
            return Optional.of(new URL(input));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<UUID> getUUID(@NotNull String input) {
        try {
            return Optional.of(UUID.fromString(input));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<byte[]> getBase64(@NotNull String input) {
        try {
            return Optional.of(Base64.getDecoder().decode(input));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
