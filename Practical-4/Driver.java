class Driver {
    public static void main(String[] args)
    {

        System.out.println("Output for PasswordChecker:-\n");
        System.out.println(PasswordChecker.getStrength("Strong#Password123"));
        System.out.println(PasswordChecker.getStrength("Strong#Password"));
        System.out.println(PasswordChecker.getStrength("Password123"));
        System.out.println(PasswordChecker.getStrength("123123"));
        System.out.println(PasswordChecker.getStrength(""));


        System.out.println("\nOutput for Chat Filter:-\n");

        String[] logs = {"10:5 Alice Hello There", "Skip this", "12:45 John Hello, What is the task Assigned to me?"};

        ChatLogFilter[] filteredChats = ChatLogFilter.filter(logs);

        StringBuilder sb = new StringBuilder("");
        String keyword = "hello";
        int matches = 0;

        for (int i = 0; i < filteredChats.length; i++) {

            if (filteredChats[i] == null) {
                continue;
            }

            if (filteredChats[i].getMessage().toLowerCase().contains(keyword.toLowerCase())) {
                sb.append(filteredChats[i].getTime() + " " + filteredChats[i].getUser() + ": " + filteredChats[i].getMessage() + "\n");
                matches++;
            }
        }

        System.out.println("Matches: " + matches);
        System.out.print(sb);

    }
}