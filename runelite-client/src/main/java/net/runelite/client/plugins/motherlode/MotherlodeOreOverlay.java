/*
 * Copyright (c) 2019, Sir Girion <https://github.com/sirgirion>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.motherlode;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class MotherlodeOreOverlay extends OverlayPanel
{
	private final MotherlodePlugin plugin;
	private final MotherlodeSession motherlodeSession;
	private final MotherlodeConfig config;

	@Inject
	MotherlodeOreOverlay(MotherlodePlugin plugin, MotherlodeSession motherlodeSession, MotherlodeConfig config)
	{
		setPosition(OverlayPosition.TOP_LEFT);
		this.plugin = plugin;
		this.motherlodeSession = motherlodeSession;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isInMlm() || !config.showOresFound())
		{
			return null;
		}

		MotherlodeSession session = motherlodeSession;

		int nuggetsFound = session.getNuggetsFound();
		int coalFound = session.getCoalFound();
		int goldFound = session.getGoldFound();
		int mithrilFound = session.getMithrilFound();
		int adamantiteFound = session.getAdamantiteFound();
		int runiteFound = session.getRuniteFound();

		// If no ores have even been collected, don't bother showing anything
		if (nuggetsFound == 0 && coalFound == 0 && goldFound == 0 && mithrilFound == 0
			&& adamantiteFound == 0 && runiteFound == 0)
		{
			return null;
		}

		panelComponent.getChildren().add(TitleComponent.builder().text("Ores found").build());

		if (nuggetsFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Nuggets:")
				.right(Integer.toString(nuggetsFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Nuggets/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getNuggetsPerHour()) : "")
					.build());
		}

		if (coalFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Coal:")
				.right(Integer.toString(coalFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Coal/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getCoalPerHour()) : "")
					.build());
		}

		if (goldFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Gold:")
				.right(Integer.toString(goldFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Gold/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getGoldPerHour()) : "")
					.build());
		}

		if (mithrilFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Mithril:")
				.right(Integer.toString(mithrilFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Mithril/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getMithrilPerHour()) : "")
					.build());
		}

		if (adamantiteFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Adamantite:")
				.right(Integer.toString(adamantiteFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Adamantite/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getAdamantitePerHour()) : "")
					.build());
		}

		if (runiteFound > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Runite:")
				.right(Integer.toString(runiteFound))
				.build());

			panelComponent.getChildren().add(LineComponent.builder()
					.left("Runite/hr:")
					.right(session.getRecentMined() > 2 ? Integer.toString(session.getRunitePerHour()) : "")
					.build());
		}

		return super.render(graphics);
	}
}