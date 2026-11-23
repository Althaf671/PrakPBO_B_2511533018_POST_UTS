package shop;

public final class StudentInfo
{
    public static final String NIM = "2511533018";
    public static final String NAME = "Muhammad Althaf Mulya";

    private StudentInfo()
    {
    }

    public static long walletBalance()
    {
        return walletBalance(NIM);
    }

    public static int coffeeStock()
    {
        return coffeeStock(NIM);
    }

    public static int rendangStock()
    {
        return rendangStock(NIM);
    }

    public static int stockFor(String sku)
    {
        return switch (sku)
        {
            case "KOPI-001" -> coffeeStock();
            case "RDG-002" -> rendangStock();
            default -> 5;
        };
    }

    public static long walletBalance(String nim)
    {
        return 150_000 + (lastTwoDigits(nim) % 4) * 50_000L;
    }

    public static int coffeeStock(String nim)
    {
        return 2 + (lastTwoDigits(nim) % 10) % 3;
    }

    public static int rendangStock(String nim)
    {
        return 1 + (lastTwoDigits(nim) / 10) % 2;
    }

    private static int lastTwoDigits(String nim)
    {
        String digits = nim.replaceAll("\\D", "");
        if (digits.length() < 2)
        {
            return 0;
        }

        return Integer.parseInt(digits.substring(digits.length() - 2));
    }
}
