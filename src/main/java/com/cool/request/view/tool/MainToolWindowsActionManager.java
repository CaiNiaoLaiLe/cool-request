/*
 * Copyright 2024 XIN LIN HOU<hxl49508@gmail.com>
 * MainToolWindowsActionManager.java is part of Cool Request
 *
 * License: GPL-3.0+
 *
 * Cool Request is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cool Request is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cool Request.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cool.request.view.tool;

import com.cool.request.common.icons.KotlinCoolRequestIcons;
import com.cool.request.view.component.StaticResourceServerPage;
import com.cool.request.view.dialog.SettingDialog;
import com.intellij.openapi.project.Project;
import kotlin.jvm.functions.Function0;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 左侧图标管理器，后续可能考虑到会自定义管理器，但都需要保留一个设置
 */
public abstract class MainToolWindowsActionManager {
    private final List<MainToolWindowsAction> actions = new ArrayList<>();
    private final Project project;
    public MainToolWindowsActionManager(Project project) {
        this.project = project;
        init();

        registerAction(createMainToolWindowsAction(
                StaticResourceServerPage.PAGE_NAME,
                KotlinCoolRequestIcons.INSTANCE.getSTATIC_WEB_SERVER(),
                () -> new StaticResourceServerPage(getProject()), false));

        actions.add(new MainToolWindowsAction("Setting", KotlinCoolRequestIcons.INSTANCE
                .getSETTING(), e -> SettingDialog.show(project)));
    }

    public Project getProject() {
        return project;
    }

    protected void init() {
    }

    public void registerAction(MainToolWindowsAction action) {
        if (action != null) actions.add(action);
    }

    public List<MainToolWindowsAction> getActions() {
        return actions;
    }

    protected MainToolWindowsAction createMainToolWindowsAction(String name,
                                                                Function0<Icon> icon,
                                                                MainToolWindowsAction.ViewFactory viewFactory, boolean lazyLoad) {
        return new MainToolWindowsAction(name, icon, viewFactory, lazyLoad);
    }

}
