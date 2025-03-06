package com.playerstatus;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RunRuneLite
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PlayerStatusHighlightPlugin.class);
		RuneLite.main(args);
	}
}