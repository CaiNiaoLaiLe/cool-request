package com.hxl.plugin.springboot.invoke.view.widget;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.impl.ActionButton;
import icons.MyIcons;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.ActionListener;

public class SendButton extends ActionButton {
    private SendAnAction sendAnAction;

    private SendButton(@NotNull AnAction action, Presentation presentation, String place, @NotNull Dimension minimumSize) {
        super(action, presentation, place, minimumSize);
        sendAnAction = ((SendAnAction) action);
    }

    private SendButton(AnAction action, Presentation presentation) {
        this(action, presentation, "send", new Dimension(32, 32));
    }

    public static SendButton newSendButton() {
        Presentation presentation = new Presentation();
        presentation.setIcon(MyIcons.SEND);
        SendButton sendButton = new SendButton(new SendAnAction(null), presentation);
        return sendButton;
    }

    public void addActionListener(ActionListener actionListener) {
        if (sendAnAction != null) {
            sendAnAction.actionListener = actionListener;
        }
    }

    static class SendAnAction extends AnAction {
        private ActionListener actionListener;

        public SendAnAction(ActionListener actionListener) {
            this.actionListener = actionListener;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            actionListener.actionPerformed(null);
        }
    }
}
