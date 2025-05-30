package com.playerstatus;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

public class PlayerStatusHighlightOverlay extends Overlay {

    private final Client client;
    private final PlayerStatusHighlightConfig config;
    private final ModelOutlineRenderer renderer;
    private final PlayerStatusHighlightPlugin plugin;

    @Inject
    public PlayerStatusHighlightOverlay(Client client, PlayerStatusHighlightPlugin plugin, PlayerStatusHighlightConfig config, ModelOutlineRenderer renderer) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.renderer = renderer;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        PlayerStatusHighlightPlugin.HighlightProperties hp = plugin.getHighlightProperties();
        renderer.drawOutline(client.getLocalPlayer(), hp.getOutlineWidth(), hp.getColor(), hp.getFeather());
        // do Mark of Darkness one separately
        if (plugin.getMarkHighlightStatus()) {
            PlayerStatusHighlightPlugin.HighlightProperties hpMark = plugin.getMarkHighlightProperties();
            renderer.drawOutline(client.getLocalPlayer(), hpMark.getOutlineWidth(), hpMark.getColor(), hpMark.getFeather());
        }
        return null;
    }
}
