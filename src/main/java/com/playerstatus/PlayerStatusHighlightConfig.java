package com.playerstatus;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

import java.awt.*;

@ConfigGroup("playerstatushighlight")
public interface PlayerStatusHighlightConfig extends Config
{
	@ConfigItem(
			position = 0,
			keyName = "expandMultiHighlights",
			name = "Expand multiple highlights",
			description = "If true, lower priority highlight colors will have their highlight widths increased<br>" +
					"for improved visibility. If false, settings specified here will be used instead."
	)
	default boolean expandMultiHighlights() { return true; }

	@ConfigSection(
			name = "Divine Potions",
			description = "Change divine potion display settings",
			position = 1
	)
	String DivinePotionHighlight = "Divine Potions";

		@ConfigItem(
				position = 1,
				keyName = "showDivine",
				name = "Highlight for divine potion duration",
				description = "Toggles displaying an outline when the player is under the effect of a divine potion",
				section = DivinePotionHighlight
		)
		default boolean showDivine()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "divineColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with when under the effect of a divine potion",
				section = DivinePotionHighlight
		)
		default Color divineColor() {
			return new Color(0xFFFFFFFF, true);
		}

		@ConfigItem(
				position = 3,
				keyName = "divineThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the divine potion highlight",
				section = DivinePotionHighlight
		)
		default int divineThickness() { return 2; }

		@ConfigItem(
				position = 4,
				keyName = "divineFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the divine potion highlight (max 4)",
				section = DivinePotionHighlight
		)
		@Range(max = 4)
		default int divineFeather() { return 0; }

	@ConfigSection(
			name = "Poison Immunity",
			description = "Change poison immunity display settings",
			position = 2
	)
	String PoisonImmunityHighlight = "Poison Immunity Highlight";

		@ConfigItem(
			position = 1,
			keyName = "showPoison",
			name = "Highlight Poison Immunity",
			description = "Toggles displaying an outline when the player is immune to poison",
			section = PoisonImmunityHighlight
		)
		default boolean showPoison()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "poisonImmunityColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with when poison immune",
				section = PoisonImmunityHighlight
		)
		default Color poisonImmunityColor() {
			return new Color(87, 210, 0, 100);
		}

		@ConfigItem(
				position = 3,
				keyName = "poisonThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the poison immunity highlight",
				section = PoisonImmunityHighlight
		)
		default int poisonThickness() { return 4; }

		@ConfigItem(
				position = 4,
				keyName = "poisonFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the poison immunity highlight (max 4)",
				section = PoisonImmunityHighlight
		)
		@Range(max = 4)
		default int poisonFeather() { return 4; }


	@ConfigSection(
			name = "Dragonfire Immunity",
			description = "Change dragonfire immunity display settings",
			position = 3
	)
	String DragonfireImmunityHighlight = "Dragonfire Immunity Highlight";

		@ConfigItem(
				position = 1,
				keyName = "showDragonfire",
				name = "Highlight Dragonfire Immunity",
				description = "Toggles displaying an outline when the player is immune to dragonfire",
				section = DragonfireImmunityHighlight
		)
		default boolean showDragonfire()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "dragonfireImmunityColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with when dragonfire immune",
				section = DragonfireImmunityHighlight
		)
		default Color dragonfireImmunityColor() {
			return new Color(107, 0, 149, 100);
		}

		@ConfigItem(
				position = 3,
				keyName = "dragonfireThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the dragonfire immunity highlight",
				section = DragonfireImmunityHighlight
		)
		default int dragonfireThickness() { return 4; }

		@ConfigItem(
				position = 4,
				keyName = "dragonfireFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the dragonfire immunity highlight (max 4)",
				section = DragonfireImmunityHighlight
		)
		@Range(max = 4)
		default int dragonfireFeather() { return 4; }


	@ConfigSection(
			name = "Venom Immunity",
			description = "Change venom immunity display settings",
			position = 4
	)
	String VenomImmunityHighlight = "Venom Immunity Highlight";

		@ConfigItem(
				position = 1,
				keyName = "showVenom",
				name = "Highlight Venom Immunity",
				description = "Toggles displaying an outline when the player is immune to venom",
				section = VenomImmunityHighlight
		)
		default boolean showVenom()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "venomImmunityColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with when venom immune",
				section = VenomImmunityHighlight
		)
		default Color venomImmunityColor() {
			return new Color(0, 147, 70, 100);
		}

		@ConfigItem(
				position = 3,
				keyName = "venomThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the venom immunity highlight",
				section = VenomImmunityHighlight
		)
		default int venomThickness() { return 4; }

		@ConfigItem(
				position = 4,
				keyName = "venomFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the venom immunity highlight (max 4)",
				section = VenomImmunityHighlight
		)
		@Range(max = 4)
		default int venomFeather() { return 4; }

	@ConfigSection(
			name = "Super Dragonfire Immunity",
			description = "Change super dragonfire immunity display settings",
			position = 5
	)
	String SuperDragonfireImmunityHighlight = "Super Dragonfire Immunity Highlight";

		@ConfigItem(
				position = 1,
				keyName = "showSuperDragonfire",
				name = "Highlight Super Dragonfire Immunity",
				description = "Toggles displaying an outline when the player is super immune to dragonfire",
				section = SuperDragonfireImmunityHighlight
		)
		default boolean showSuperDragonfire()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "superDragonfireImmunityColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with when super dragonfire immune",
				section = SuperDragonfireImmunityHighlight
		)
		default Color superDragonfireImmunityColor() {
			return new Color(190, 107, 255, 100);
		}

		@ConfigItem(
				position = 3,
				keyName = "superDragonfireThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the super dragonfire immunity highlight",
				section = SuperDragonfireImmunityHighlight
		)
		default int superDragonfireThickness() { return 4; }

		@ConfigItem(
				position = 4,
				keyName = "superDragonfireFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the super dragonfire immunity highlight (max 4)",
				section = SuperDragonfireImmunityHighlight
		)
		@Range(max = 4)
		default int superDragonfireFeather() { return 4; }

	@ConfigSection(
			name = "Mark of Darkness",
			description = "Change Mark of Darkness display settings",
			position = 6
	)
	String MarkHighlight = "Mark of Darkness Highlight";

		@ConfigItem(
				position = 1,
				keyName = "showMark",
				name = "Highlight Mark of Darkness buff",
				description = "Toggles displaying an outline when the player is under the effect of Mark of Darkness",
				section = MarkHighlight
		)
		default boolean showMark()
		{
			return true;
		}

		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "markColor",
				name = "Highlight Color",
				description = "Selects the color to highlight the player with while Mark of Darkness is active",
				section = MarkHighlight
		)
		default Color markColor() {
			return new Color(0x965500CD, true);
		}

		@ConfigItem(
				position = 3,
				keyName = "markThickness",
				name = "Highlight Thickness",
				description = "Controls the thickness of the Mark of Darkness highlight",
				section = MarkHighlight
		)
		default int markThickness() { return 2; }

		@ConfigItem(
				position = 4,
				keyName = "markFeather",
				name = "Highlight Feather",
				description = "Controls the feather/fading effect of the Mark of Darkness highlight (max 4)",
				section = MarkHighlight
		)
		@Range(max = 4)
		default int markFeather() { return 0; }

	@ConfigSection(
			name = "Combinations",
			description = "Configure settings for multiple simultaneous immunities",
			position = 7
	)
		String Combinations = "Combinations";
		@Alpha
		@ConfigItem(
				position = 1,
				keyName = "superDragonfireAndVenomImmunityColor",
				name = "Super Antifire + Venom Immunity",
				description = "Selects the color to highlight the player with when immune to super dragonfire and venom.<br>" +
						"Thickness and feather settings will be inherited from venom immunity settings.",
				section = Combinations
		)
		default Color superDragonfireAndVenomImmunityColor() {
			return new Color(255, 196, 0, 100);
		}
		@Alpha
		@ConfigItem(
				position = 2,
				keyName = "superDragonfireAndPoisonImmunityColor",
				name = "Super Antifire + Poison Immunity",
				description = "Selects the color to highlight the player with when immune to super dragonfire and poison " +
						"(but not venom!).<br>" +
						"Thickness and feather settings will be inherited from poison immunity settings.",
				section = Combinations
		)
		default Color superDragonfireAndPoisonImmunityColor() {
			return new Color(255, 60, 0, 100);
		}
		@Alpha
		@ConfigItem(
				position = 3,
				keyName = "dragonfireAndVenomImmunityColor",
				name = "Antifire + Venom Immunity",
				description = "Selects the color to highlight the player with when immune to dragonfire and venom.<br>" +
						"Thickness and feather settings will be inherited from venom immunity settings.",
				section = Combinations
		)
		default Color dragonfireAndVenomImmunityColor() {
			return new Color(255, 145, 0, 100);
		}
		@Alpha
		@ConfigItem(
				position = 4,
				keyName = "dragonfireAndPoisonImmunityColor",
				name = "Antifire + Poison Immunity",
				description = "Selects the color to highlight the player with when immune to dragonfire and poison " +
						"(but not venom!).<br>Thickness and feather settings will be inherited from poison immunity settings.",
				section = Combinations
		)
		default Color dragonfireAndPoisonImmunityColor() {
			return new Color(191, 255, 0, 100);
	}
}
