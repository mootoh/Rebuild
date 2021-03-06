package rejasupotaro.rebuild.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import rejasupotaro.rebuild.R;
import rejasupotaro.rebuild.activities.EpisodeDetailActivity;
import rejasupotaro.rebuild.events.BusProvider;
import rejasupotaro.rebuild.events.ReceivePauseActionEvent;
import rejasupotaro.rebuild.events.ReceiveResumeActionEvent;
import rejasupotaro.rebuild.media.PodcastPlayer;
import rejasupotaro.rebuild.models.Episode;
import rejasupotaro.rebuild.services.PodcastPlayerService;
import rejasupotaro.rebuild.utils.DateUtils;

public class PodcastPlayerNotification {

    private static final int NOTIFICATION_ID = 1;

    private static final String ACTION_TOGGLE_PLAYBACK = "action_toggle_playback";

    private static boolean isInBackground = false;
    public static void setIsInBackground(boolean isinb) {
        isInBackground = isinb;
    }

    public static void notify(Context context, Episode episode) {
        notify(context, episode, 0);
    }

    public static void notify(Context context, Episode episode, int currentPosition) {
        if (!isInBackground || episode == null || context == null) return;
        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, build(context, episode, currentPosition));
    }

    private static Notification build(Context context, Episode episode, int currentPosition) {
        Intent pauseIntent = new Intent(context, PodcastPlayerService.class);
        pauseIntent.setAction(ACTION_TOGGLE_PLAYBACK);
        PendingIntent piToggle = PendingIntent.getService(
                context, 0, pauseIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(episode.getTitle());
        builder.setContentText(episode.getDescription());

        if (PodcastPlayer.getInstance().isPlaying()) {
            builder.addAction(android.R.drawable.ic_media_pause, context.getString(R.string.notification_pause), piToggle);
        } else {
            builder.addAction(android.R.drawable.ic_media_play, context.getString(R.string.notification_resume), piToggle);
        }

        builder.setProgress(DateUtils.durationToInt(episode.getDuration()), currentPosition, false);

        PendingIntent launchDetail = PendingIntent.getActivity(context, 0,
                EpisodeDetailActivity.createIntent(context, episode.getEpisodeId()), Intent.FLAG_ACTIVITY_NEW_TASK);
        builder.setContentIntent(launchDetail);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;

        return notification;
    }

    public static void cancel(Context context) {
        if (context == null) return;

        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    public static void handleAction(Context context, String action) {
        if (TextUtils.isEmpty(action)) return;

        if (action.equals(ACTION_TOGGLE_PLAYBACK)) {
            if (PodcastPlayer.getInstance().isPlaying()) {
                PodcastPlayer.getInstance().pause();
                BusProvider.getInstance().post(new ReceivePauseActionEvent());
            } else {
                PodcastPlayer.getInstance().start();
                BusProvider.getInstance().post(new ReceiveResumeActionEvent());
            }
            // Update the notification itself
            PodcastPlayerNotification.notify(context, PodcastPlayer.getInstance().getEpisode(), PodcastPlayer.getInstance().getCurrentPosition());
        }
    }
}
