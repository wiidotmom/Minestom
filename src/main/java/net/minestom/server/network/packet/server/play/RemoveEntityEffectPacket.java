package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.minestom.server.network.NetworkBuffer.VAR_INT;

public record RemoveEntityEffectPacket(int entityId, @NotNull PotionEffect potionEffect) implements ServerPacket.Play {
    public static final NetworkBuffer.Type<RemoveEntityEffectPacket> SERIALIZER = new NetworkBuffer.Type<>() {
        @Override
        public void write(@NotNull NetworkBuffer buffer, RemoveEntityEffectPacket value) {
            buffer.write(VAR_INT, value.entityId);
            buffer.write(VAR_INT, value.potionEffect.id());
        }

        @Override
        public RemoveEntityEffectPacket read(@NotNull NetworkBuffer buffer) {
            return new RemoveEntityEffectPacket(buffer.read(VAR_INT), Objects.requireNonNull(PotionEffect.fromId(buffer.read(VAR_INT))));
        }
    };
}
