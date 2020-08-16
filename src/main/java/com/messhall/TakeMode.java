package com.messhall;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.MenuAction;

@Getter
@RequiredArgsConstructor
public enum TakeMode
{
	TAKE_1("Take", MenuAction.CC_OP, 1),
	TAKE_5("Take-5", MenuAction.CC_OP, 2),
	TAKE_10("Take-10", MenuAction.CC_OP, 3),
	TAKE_50("Take-50", MenuAction.CC_OP, 4),
	TAKE_X("Take-X", MenuAction.CC_OP, 5),
	OFF("Off", MenuAction.UNKNOWN, 0);

	private final String name;

	private final MenuAction menuAction;

	private final int identifier;

	@Override
	public String toString()
	{
		return name;
	}
}
