class ChatLogFilter {
    private String time;
    private String user;
    private String msg;

    ChatLogFilter(String time, String user, String msg) {
        this.time = time;
        this.user = user;
        this.msg = msg;
    }

    public static ChatLogFilter[] filter(String[] logs) {
        ChatLogFilter[] chats = new ChatLogFilter[logs.length];

        for (int i = 0; i < logs.length; i++) {
            String[] temp = logs[i].split(" ", 3);

            if (temp.length < 3) {
                chats[i] = null;
                continue;
            }

            chats[i] = new ChatLogFilter(temp[0], temp[1], temp[2]);
        }

        return chats;
    }

    public String getUser() { return user; }
    public String getTime() { return time; }
    public String getMessage() { return msg; }
}