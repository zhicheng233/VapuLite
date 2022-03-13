package gq.vapulite.Manager;


import gq.vapulite.Vapu.modules.Module;
import gq.vapulite.utils.Notification;

import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationManager {
    private static final CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<>();

    public static void doRender(float wid, float hei) {
            float startY = hei - 23;
            for (Notification notification : notifications) {
                if (notification == null)
                    continue;
                notification.draw(wid, startY);
                startY -= notification.getHeight() + 6;
            }
            notifications.removeIf(Notification::shouldDelete);
    }

    public static void show(String title, String message, Notification.Type type) {
        notifications.add(new Notification(title, message, type, 2500L));
    }

    public static void show(String title, String message, Notification.Type type, long stayTime) {
        notifications.add(new Notification(title, message, type, stayTime));
    }

    public static void show(String title, String message, Module module) {
        notifications.add(new Notification(title, message, Notification.Type.MODULE, 2500L, module));
    }
}
