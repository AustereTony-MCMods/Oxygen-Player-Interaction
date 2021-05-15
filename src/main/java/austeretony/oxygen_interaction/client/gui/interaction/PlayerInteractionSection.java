package austeretony.oxygen_interaction.client.gui.interaction;

import austeretony.oxygen_core.client.gui.base.GUIUtils;
import austeretony.oxygen_core.client.gui.base.background.Background;
import austeretony.oxygen_core.client.gui.base.common.ListEntry;
import austeretony.oxygen_core.client.gui.base.core.Section;
import austeretony.oxygen_core.client.gui.base.list.ScrollableList;
import austeretony.oxygen_core.client.interaction.InteractionHelper;
import austeretony.oxygen_core.client.interaction.PlayerInteractionMenuEntry;
import austeretony.oxygen_core.client.settings.CoreSettings;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInteractionSection extends Section {

    private final PlayerInteractionScreen screen;
    private final List<PlayerInteractionMenuEntry> entriesList;

    public PlayerInteractionSection(@Nonnull PlayerInteractionScreen screen) {
        super(screen, "", true);
        this.screen = screen;
        entriesList = InteractionHelper.getPlayerInteractionsMap().values()
                .stream()
                .sorted(Comparator.comparing(PlayerInteractionMenuEntry::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void init() {
        addWidget(new Background.Default(this));

        ScrollableList<Integer> scrollableList;
        addWidget(scrollableList = new ScrollableList<>(0, 0, entriesList.size() + 1,
                getWorkspace().getWidth(), PlayerInteractionScreen.MENU_ENTRY_HEIGHT)
                .<Integer>setEntryMouseClickListener((previous, current, x, y, button) -> {
                    if (current.getEntry() < entriesList.size()) {
                        if (entriesList.get(current.getEntry()).process(screen.getPlayerUUID())) {
                            screen.close();
                        }
                    } else {
                        screen.close();
                    }
                }));

        for (int i = 0; i < entriesList.size(); i++) {
            PlayerInteractionMenuEntry entry = entriesList.get(i);
            ListEntry listEntry = ListEntry.of(entry.getDisplayName(screen.getPlayerUUID()), i);
            scrollableList.addElement(listEntry);
        }
        scrollableList.addElement(ListEntry.of("oxygen_core.gui.button.close", entriesList.size()));
    }

    @Override
    public void drawMiddleLayer(int mouseX, int mouseY, float partialTicks) {
        super.drawMiddleLayer(mouseX, mouseY, partialTicks);

        GUIUtils.drawRect(-2, -1, -1.5, getHeight() + 1.0, CoreSettings.COLOR_TEXT_BASE_ENABLED.asInt());

        float textScale = CoreSettings.SCALE_TEXT_BASE.asFloat();
        float textWidth = GUIUtils.getTextWidth(screen.getPlayerUsername(), textScale);
        float textHeight = GUIUtils.getTextHeight(textScale);
        GUIUtils.drawString(screen.getPlayerUsername(), -textWidth - 6, ((entriesList.size() + 1) * PlayerInteractionScreen.MENU_ENTRY_HEIGHT) / 2F - textHeight / 2F,
                textScale, CoreSettings.COLOR_TEXT_BASE_ENABLED.asInt(), true);
    }
}
