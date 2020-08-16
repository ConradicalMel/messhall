package com.messhall;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(MessHallConfig.GROUP)
public interface MessHallConfig extends Config
{
	String GROUP = "messhall";

	@ConfigItem(
		keyName = "messHallSwaps",
		name = "Swap left-click",
		description = "Changes the default left-click on items within the Mess Hall",
		position = 1
	)
	default boolean messHallSwaps()
	{
		return true;
	}

	@ConfigItem(
		keyName = "itemTake",
		name = "Item Swap",
		description = "Swaps mess hall options when gathering items",
		position = 2
	)
	default TakeMode itemTake()
	{
		return TakeMode.OFF;
	}
}
