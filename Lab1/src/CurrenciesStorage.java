import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class CurrenciesStorage
{
    private static CurrenciesStorage instance;
    private HashMap<String, Currency> currencies;

    public Currency getCurrency(String ticker)
    {
        Currency x;
        x = currencies.get(ticker);
        return x;
    }

    public static CurrenciesStorage getInstance()
    {
        if (instance == null)
            instance = new CurrenciesStorage();
       return instance;
    }

    private CurrenciesStorage()
    {
        this.currencies = new HashMap<>();
        DataDownload download = DataDownload.getInstance();
        List<String> data = download.getData();
        for(int i = 0; i <= (data.size() / 4) - 1; i++)
        {
            int x = i * 4;
            String value = data.get(x + 1);
            value = value.replace(",", ".");
            BigDecimal factor = new BigDecimal(value);

            value = data.get(x + 3);
            value = value.replace(",", ".");
            BigDecimal price = new BigDecimal(value);

            Currency tmp = new Currency(data.get(x), data.get(x + 2), factor, price);
            this.currencies.put(tmp.getTicker(), tmp);
        }
    }

    public String[] getCurrencyNamesArray()
    {
        return currencies.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " (" + entry.getValue().getName() + ")")
                .sorted()
                .toArray(String[]::new);
    }

}
