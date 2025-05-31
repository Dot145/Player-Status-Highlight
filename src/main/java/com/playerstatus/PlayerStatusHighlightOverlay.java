package com.playerstatus;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
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
        // order: (smallest) divine -> Mark of Darkness -> immunities (biggest)
        // but we need to draw them from biggest to smallest
        boolean expandHighlights = config.expandMultiHighlights();
        PlayerStatusHighlightPlugin.HighlightProperties hp = plugin.getHighlightProperties();
        PlayerStatusHighlightPlugin.HighlightProperties hpDivine = plugin.getDivineHighlightProperties();
        PlayerStatusHighlightPlugin.HighlightProperties hpMark = plugin.getMarkHighlightProperties();
        int immunityOutlineWidth = hp.getOutlineWidth();
        if (expandHighlights) {
            if (plugin.getDivineHighlightStatus()) {
                immunityOutlineWidth += hpDivine.getOutlineWidth();
            }
            if (plugin.getMarkHighlightStatus()) {
                immunityOutlineWidth += hpMark.getOutlineWidth();
            }
        }
        renderer.drawOutline(client.getLocalPlayer(), immunityOutlineWidth, hp.getColor(), hp.getFeather());
        if (plugin.getMarkHighlightStatus()) {
            int outlineWidth = hpMark.getOutlineWidth();
            if (config.expandMultiHighlights() && plugin.getDivineHighlightStatus()) outlineWidth += hpDivine.getOutlineWidth();
            renderer.drawOutline(client.getLocalPlayer(), outlineWidth, hpMark.getColor(), hpMark.getFeather());
        }
        if (plugin.getDivineHighlightStatus()) {
            renderer.drawOutline(client.getLocalPlayer(), hpDivine.getOutlineWidth(), hpDivine.getColor(), hpDivine.getFeather());
        }

        return null;
    }
}
