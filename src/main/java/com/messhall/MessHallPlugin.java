package com.messhall;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "Mess Hall Utilities",
	description = "Default left-click options and other Mess Hall qol changes",
	tags = {"food", "kitchen", "zeah", "cooking"},
	enabledByDefault = true
)
public class MessHallPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MessHallConfig config;

	@Provides
	MessHallConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MessHallConfig.class);
	}

	@Override
	protected void startUp()
	{
		log.info("Mess Hall plugin started!");
	}

	@Override
	protected void shutDown()
	{
		log.info("Mess Hall plugin stopped!");
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded)
	{
		String option = Text.removeTags(menuEntryAdded.getOption().toLowerCase());

		if (!option.startsWith("take"))
		{
			return;
		}
		if (config.messHallSwaps() && config.itemTake() != TakeMode.OFF)
		{
			TakeMode takeMode = config.itemTake();
			final int actionId = takeMode.getMenuAction().getId();
			final int opId = takeMode.getIdentifier();

			takeModeSwap(actionId, opId);
		}
	}

	private void takeModeSwap(int entryTypeId, int entryIdentifier)
	{
		MenuEntry[] menuEntries = client.getMenuEntries();

		for (int i = menuEntries.length - 1; i >= 0; --i)
		{
			MenuEntry entry = menuEntries[i];

			if (entry.getType() == entryTypeId && entry.getIdentifier() == entryIdentifier)
			{
				entry.setType(MenuAction.CC_OP.getId());

				menuEntries[i] = menuEntries[menuEntries.length - 1];
				menuEntries[menuEntries.length - 1] = entry;

				client.setMenuEntries(menuEntries);
				break;
			}
		}
	}
}
