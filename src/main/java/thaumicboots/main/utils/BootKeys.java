package thaumicboots.main.utils;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicboots.api.serverfiles.PacketHandler;
import thaumicboots.api.serverfiles.PacketJumpToggle;
import thaumicboots.api.serverfiles.PacketSpeedToggle;

public class BootKeys {

    private static final KeyBinding keyJumpToggle = new KeyBinding(
            "keybinding.jumptoggle",
            Keyboard.KEY_MINUS,
            "Thaumic Boots");
    private final KeyBinding keySpeedToggle = new KeyBinding(
            "keybinding.speedtoggle",
            Keyboard.KEY_ADD,
            "Thaumic Boots");

    public BootKeys() {
        FMLCommonHandler.instance().bus().register(this);
        ClientRegistry.registerKeyBinding(this.keyJumpToggle);
        ClientRegistry.registerKeyBinding(this.keySpeedToggle);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyPressed(TickEvent.ClientTickEvent event) throws InterruptedException {
        checkKeys();
    }

    @SideOnly(Side.CLIENT)
    private void checkKeys() throws InterruptedException {
        if (keyJumpToggle.getIsKeyPressed()) {
            toggleJump();
        } else if (keySpeedToggle.getIsKeyPressed()) {
            toggleSpeed();
        }
    }

    @SideOnly(Side.CLIENT)
    private static void toggleJump() {
        PacketHandler.INSTANCE.sendToServer(new PacketJumpToggle());
    }

    @SideOnly(Side.CLIENT)
    private static void toggleSpeed() {
        PacketHandler.INSTANCE.sendToServer(new PacketSpeedToggle());
    }
}
