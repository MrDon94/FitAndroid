package com.github.fitandroid.oreo;

/**
 * 权限组，方便申请权限时直接获取它，一次性将它们申请了。防止遗漏某个权限导致的异常。
 * 官方原文：
 * 在 Android 8.0 之前，如果应用在运行时请求权限并且被授予该权限，系统会错误地将属于同一权限组并且在清单中注
 * 册的其他权限也一起授予应用。对于针对 Android 8.0 的应用，此行为已被纠正。系统只会授予应用明确请求的权限。
 * 然而，一旦用户为应用授予某个权限，则所有后续对该权限组中权限的请求都将被自动批准。
 * 例如，假设某个应用在其清单中列出 READ_EXTERNAL_STORAGE 和 WRITE_EXTERNAL_STORAGE。应用请求
 * READ_EXTERNAL_STORAGE，并且用户授予了该权限。如果该应用针对的是 API 级别 24 或更低级别，系统还会同时授予
 * WRITE_EXTERNAL_STORAGE，因为该权限也属于同一 STORAGE 权限组并且也在清单中注册过。如果该应用针对的是
 * Android 8.0，则系统此时仅会授予 READ_EXTERNAL_STORAGE；不过，如果该应用后来又请求
 * WRITE_EXTERNAL_STORAGE，则系统会立即授予该权限，而不会提示用户。
 */
public final class Permission8 {

    private Permission8() {}

    /** 外部存储权限（特殊权限，需要 Android 11 及以上） */
    public static final String MANAGE_EXTERNAL_STORAGE = "android.permission.MANAGE_EXTERNAL_STORAGE";

    /** 应用安装权限（特殊权限，需要 Android 8.0 及以上） */
    public static final String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES";

    /** 通知栏权限（特殊权限，需要 Android 7.0 及以上） */
    public static final String NOTIFICATION_SERVICE = "android.permission.ACCESS_NOTIFICATION_POLICY";

    /** 悬浮窗权限（特殊权限，需要 Android 6.0 及以上） */
    public static final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";

    /** 系统设置权限（特殊权限，需要 Android 6.0 及以上） */
    public static final String WRITE_SETTINGS = "android.permission.WRITE_SETTINGS";

    /**
     * 读取外部存储
     *
     * @deprecated         在 Android 11 已经废弃，请直接使用 {@link Permission8#MANAGE_EXTERNAL_STORAGE}
     */
    @Deprecated
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    /**
     * 写入外部存储
     *
     * @deprecated         在 Android 11 已经废弃，请直接使用 {@link Permission8#MANAGE_EXTERNAL_STORAGE}
     */
    @Deprecated
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    /** 读取日历 */
    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    /** 修改日历 */
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";

    /** 相机权限 */
    public static final String CAMERA = "android.permission.CAMERA";

    /** 读取联系人 */
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    /** 修改联系人 */
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    /** 访问账户列表 */
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";

    /** 获取精确位置 */
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    /** 获取粗略位置 */
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    /** 在后台获取位置（需要 Android 10.0 及以上） */
    public static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";

    /** 读取照片中的地理位置（需要 Android 10.0 及以上）*/
    public static final String ACCESS_MEDIA_LOCATION = "android.permission.ACCESS_MEDIA_LOCATION";

    /** 录音权限 */
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";

    /** 读取电话状态 */
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    /** 拨打电话 */
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";
    /** 读取通话记录 */
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    /** 修改通话记录 */
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    /** 添加语音邮件 */
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    /** 使用SIP视频 */
    public static final String USE_SIP = "android.permission.USE_SIP";
    /**
     * 处理拨出电话
     *
     * @deprecated         在 Android 10 已经废弃，请直接使用 {@link Permission8#ANSWER_PHONE_CALLS}
     */
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    /** 接听电话（需要 Android 8.0 及以上） */
    public static final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";
    /** 读取手机号码（需要 Android 8.0 及以上） */
    public static final String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";

    /** 使用传感器 */
    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS";
    /** 获取活动步数（需要 Android 10.0 及以上） */
    public static final String ACTIVITY_RECOGNITION = "android.permission.ACTIVITY_RECOGNITION";

    /** 发送短信 */
    public static final String SEND_SMS = "android.permission.SEND_SMS";
    /** 接收短信 */
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    /** 读取短信 */
    public static final String READ_SMS = "android.permission.READ_SMS";
    /** 接收 WAP 推送消息 */
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    /** 接收彩信 */
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";

    ///** 允许呼叫应用继续在另一个应用中启动的呼叫（需要 Android 9.0 及以上，极其少见，所以不做适配） */
    //public static final String ACCEPT_HANDOVER = "android.permission.ACCEPT_HANDOVER";

    /**
     * 权限组
     */
    public static final class Group {

        /** 存储权限 */
        public static final String[] STORAGE = new String[]{
                Permission8.READ_EXTERNAL_STORAGE,
                Permission8.WRITE_EXTERNAL_STORAGE};

        /** 位置权限 */
        public static final String[] LOCATION = new String[]{
                Permission8.ACCESS_FINE_LOCATION,
                Permission8.ACCESS_COARSE_LOCATION,
                Permission8.ACCESS_BACKGROUND_LOCATION};

        /** 日历权限 */
        public static final String[] CALENDAR = new String[]{
                Permission8.READ_CALENDAR,
                Permission8.WRITE_CALENDAR};

        /** 联系人权限 */
        public static final String[] CONTACTS = new String[]{
                Permission8.READ_CONTACTS,
                Permission8.WRITE_CONTACTS,
                Permission8.GET_ACCOUNTS};

        /** 传感器权限 */
        public static final String[] SENSORS = new String[] {
                Permission8.BODY_SENSORS,
                Permission8.ACTIVITY_RECOGNITION};

        /**  电话权限*/
        public static String[] PHONE = new String[] {
                Permission8.READ_PHONE_STATE,
                Permission8.CALL_PHONE,
                Permission8.READ_CALL_LOG,
                Permission8.WRITE_CALL_LOG,
                Permission8.ADD_VOICEMAIL,
                Permission8.USE_SIP,
                Permission8.PROCESS_OUTGOING_CALLS,
                Permission8.ANSWER_PHONE_CALLS,
                Permission8.READ_PHONE_NUMBERS ,
        };

        /**  短信权限*/
        public static String[] SMS = new String[] {
                Permission8.SEND_SMS,
                Permission8.RECEIVE_SMS,
                Permission8.READ_SMS,
                Permission8.RECEIVE_WAP_PUSH,
                Permission8.RECEIVE_MMS
        };
    }
}