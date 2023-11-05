import java.math.*;
public class Currency
{
    private String name;
    private String ticker;
    private BigDecimal factor;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency(String name, String ticker, BigDecimal factor, BigDecimal price)
    {
        this.name = name;
        this.ticker = ticker;
        this.factor = factor;
        this.price = price;
    }

    public BigDecimal getRatio()
    {
        return this.price.divide(this.factor);
    }
}
