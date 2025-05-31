# Player Status Highlight

## Description

This plugin allows the player character to glow when under the effects of antipoison, antivenom, antifire, or super antifire potions, Mark of Darkness, or divine potions. The colors, sizes, and fading effects for each status effect can be individually customized.

Do your timers get lost in the commotion of bossing? Wish you had an easy visual indicator for your buffs? Do you keep forgetting to check the timer and ignore the sound effects and chat messages warning you that your buffs are running out? Or do you just want to look cool when all buffed up? This plugin will help with all of these issues.

## Examples

| No immunity | Poison immunity | Venom immunity | Antifire | Super antifire |
|------|------|------|------|------|
| ![no_immunity](no_immunity.png) | ![poison_immune](poison_immune.png) | ![venom_immune](venom_immune.png) | ![antifire](antifire.png) | ![superantifire](superantifire.png) |

Note that ALL highlight colors are customizable in the configuration.

## Configuration

The expand multiple highlights option changes each buff's thickness value to be the sum of the buffs on top of it plus its own thickness, which does increase the outline width but also makes it easier to see all buffs on you. See below for a demonstration. In both images, the player has Mark of Darkness active and is under the effects of a divine potion and an antipoison potion. Due to the thickness of the divine potion and Mark of Darkness highlights, the poison highlight is practically unnoticeable with extend multiple highlights off. 

| Expand multiple highlights OFF | Expand multiple highlights ON |
|----|----|
| ![off](no_expand_multiple_highlights.png) | ![on](mark_and_divine.png) |

With expand multiple highlights on, the player is more apparently ready to face Yama! (if they can get better equipment, that is...)

For each type of immunity, the user may toggle whether the player is highlighted with that immunity and configure the color, thickness, and feather/fade effect of the highlight.

![image](https://github.com/user-attachments/assets/0d9f2dd9-34c4-44c7-8850-90bb24d7faca)

The configuration menu has a "Combinations" category for color choice combinations of antifire/superantifire and poison or venom immunity.

![image](https://github.com/user-attachments/assets/bfaa0cb8-7b8e-49e8-ad26-e7d2d8f0da3b)

## Changelog

#### v1.2: 
Added a highlight for divine potions. This highlight displays over immunity and over Mark of Darkness. Also added the highlight expanding option for improved visibility when the player has multiple status effects.

#### v1.1: 
Added a highlight for Mark of Darkness, which displays over your immunity so you can confirm that you have both its effects and poison immunity for your Yama slaying.
