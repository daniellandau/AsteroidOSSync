/*
 * Copyright (C) 2016 - Florent Revest <revestflo@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.asteroidos.sync;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.Hashtable;
import java.util.Map;

public class NLService extends NotificationListenerService {
    private NLServiceReceiver nlServiceReceiver;

    private Map<String, String> iconFromPackage;

    @Override
    public void onCreate() {
        super.onCreate();
        nlServiceReceiver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("org.asteroidos.sync.NOTIFICATION_LISTENER_SERVICE");
        registerReceiver(nlServiceReceiver, filter);

        iconFromPackage = new Hashtable<>();
        iconFromPackage.put("org.buffer.android", "logo-buffer");
        iconFromPackage.put("com.android.chrome", "logo-chrome");
        iconFromPackage.put("com.chrome.beta", "logo-chrome");
        iconFromPackage.put("com.chrome.dev", "logo-chrome");
        iconFromPackage.put("com.dropbox.android", "logo-dropbox");
        iconFromPackage.put("com.facebook.groups", "logo-facebook");
        iconFromPackage.put("com.facebook.work", "logo-facebook");
        iconFromPackage.put("com.facebook.Mentions", "logo-facebook");
        iconFromPackage.put("com.facebook.katana", "logo-facebook");
        iconFromPackage.put("com.joelapenna.foursquared", "logo-foursquare");
        iconFromPackage.put("com.google.android.googlequicksearchbox", "logo-google");
        iconFromPackage.put("com.google.android.apps.plus", "logo-googleplus");
        iconFromPackage.put("com.instagram.android", "logo-instagram");
        iconFromPackage.put("com.instagram.layout", "logo-instagram");
        iconFromPackage.put("com.instagram.boomerang", "logo-instagram");
        iconFromPackage.put("com.linkedin.Coworkers", "logo-linkedin");
        iconFromPackage.put("com.linkedin.leap", "logo-linkedin");
        iconFromPackage.put("com.linkedin.android.salesnavigator", "logo-linkedin");
        iconFromPackage.put("com.linkedin.android.learning", "logo-linkedin");
        iconFromPackage.put("com.linkedin.recruiter", "logo-linkedin");
        iconFromPackage.put("net.slideshare.mobile", "logo-linkedin");
        iconFromPackage.put("com.linkedin.pulse", "logo-linkedin");
        iconFromPackage.put("com.linkedin.android.jobs.jobseeker", "logo-linkedin");
        iconFromPackage.put("com.linkedin.android", "logo-linkedin");
        iconFromPackage.put("com.pinterest", "logo-pinterest");
        iconFromPackage.put("com.scee.psxandroid", "logo-playstation");
        iconFromPackage.put("com.playstation.mobilemessenger", "logo-playstation");
        iconFromPackage.put("com.playstation.video", "logo-playstation");
        iconFromPackage.put("com.playstation.remoteplay", "logo-playstation");
        iconFromPackage.put("com.reddit.frontpage", "logo-reddit");
        iconFromPackage.put("flipboard.app", "logo-rss");
        iconFromPackage.put("com.noinnion.android.greader.reader", "logo-rss");
        iconFromPackage.put("com.devhd.feedly", "logo-rss");
        iconFromPackage.put("com.microsoft.office.lync15", "logo-skype");
        iconFromPackage.put("com.skype.android.access", "logo-skype");
        iconFromPackage.put("com.skype.raider", "logo-skype");
        iconFromPackage.put("com.snapchat.android", "logo-snapchat");
        iconFromPackage.put("com.valvesoftware.android.steam.community", "logo-steam");
        iconFromPackage.put("com.tumblr", "logo-tumblr");
        iconFromPackage.put("tv.twitch.android.app", "logo-twitch");
        iconFromPackage.put("com.twitter.android", "logo-twitter");
        iconFromPackage.put("com.vimeo.android.videoapp", "logo-vimeo");
        iconFromPackage.put("com.whatsapp", "logo-whatsapp");
        iconFromPackage.put("org.wordpress.android", "logo-wordpress");
        iconFromPackage.put("com.microsoft.xboxone.smartglass", "logo-xbox");
        iconFromPackage.put("com.microsoft.xboxone.smartglass.beta", "logo-xbox");
        iconFromPackage.put("com.yahoo.mobile.client.android.mail", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.search", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.weather", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.atom", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.im", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.finance", "logo-yahoo");
        iconFromPackage.put("com.yahoo.mobile.client.android.sportacular", "logo-yahoo");
        iconFromPackage.put("com.google.android.youtube", "logo-youtube");
        iconFromPackage.put("com.tinder", "md-flame");
        iconFromPackage.put("com.google.android.gm", "ios-mail");
        iconFromPackage.put("com.sec.android.app.music", "ios-musical-notes");
        iconFromPackage.put("com.google.android.music", "ios-musical-notes");
        iconFromPackage.put("com.spotify.music", "ios-musical-notes");
        iconFromPackage.put("org.telegram.messenger", "ios-paper-plane");
        iconFromPackage.put("com.runtastic.android", "ios-walk");
        iconFromPackage.put("com.runtastic.android.pro2", "ios-walk");
        iconFromPackage.put("com.google.android.apps.photos", "ios-images");
        iconFromPackage.put("com.google.android.apps.docs.editors.docs", "ios-document");
        iconFromPackage.put("com.google.android.videos", "ios-film");
        iconFromPackage.put("com.google.android.talk", "ios-quote");
        iconFromPackage.put("com.google.android.apps.giant", "md-analytics");
        iconFromPackage.put("com.google.android.apps.maps", "ios-map");
        iconFromPackage.put("com.google.android.calendar", "ios-calendar");
        iconFromPackage.put("com.google.android.contacts", "ios-contacts");
        iconFromPackage.put("com.facebook.orca", "ios-text");
        iconFromPackage.put("com.google.android.apps.messaging", "ios-text");
        iconFromPackage.put("com.jb.gosms", "ios-text");
        iconFromPackage.put("com.android.mms", "ios-text");
        iconFromPackage.put("com.sonyericsson.conversations", "ios-text");
        iconFromPackage.put("com.android.vending", "md-appstore");
        iconFromPackage.put("com.google.android.dialer", "ios-call");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nlServiceReceiver);
        iconFromPackage.clear();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification n = sbn.getNotification();
        if(n.priority < Notification.PRIORITY_DEFAULT)
            return;

        String packageName = sbn.getPackageName();
        int id = sbn.getId();
        String summary = n.extras.getString("android.title");
        String body = n.extras.getString("android.text");
        if(body == null)
            body = n.extras.getString("android.summaryText");

        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            ai = null;
        }
        String appName = pm.getApplicationLabel(ai).toString();
        String appIcon = iconFromPackage.get(appName);

        if(summary == null) summary = "";
        if(body == null) body = "";
        if(packageName == null) packageName = "";
        if(appName == null) appName = "";
        if(appIcon == null) appIcon = "";

        Intent i = new  Intent("org.asteroidos.sync.NOTIFICATION_LISTENER");
        i.putExtra("event", "posted");
        i.putExtra("packageName", packageName);
        i.putExtra("id", id);
        i.putExtra("appName", appName);
        i.putExtra("appIcon", appIcon);
        i.putExtra("summary", summary);
        i.putExtra("body", body);

        sendBroadcast(i);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Intent i = new  Intent("org.asteroidos.sync.NOTIFICATION_LISTENER");
        i.putExtra("event", "removed");
        i.putExtra("packageName", sbn.getPackageName());
        i.putExtra("title", sbn.getNotification().extras.getString("android.title"));

        sendBroadcast(i);
    }

    class NLServiceReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("command").equals("clearall")){
                NLService.this.cancelAllNotifications();
            }
            else if(intent.getStringExtra("command").equals("list")){
                Intent i1 = new  Intent("org.asteroidos.sync.NOTIFICATION_LISTENER");
                i1.putExtra("notification_event","=====================");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification sbn : NLService.this.getActiveNotifications()) {
                    Intent i2 = new  Intent("org.asteroidos.sync.NOTIFICATION_LISTENER");
                    i2.putExtra("notification_event",i +" " + sbn.getPackageName() + "n");
                    sendBroadcast(i2);
                    i++;
                }
                Intent i3 = new  Intent("org.asteroidos.sync.NOTIFICATION_LISTENER");
                i3.putExtra("notification_event","===== Notification List ====");
                sendBroadcast(i3);
            }
        }
    }
}