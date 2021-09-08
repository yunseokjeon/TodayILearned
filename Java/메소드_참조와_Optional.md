```Java
class IBox{
    private int n;
    public IBox(int i) { n = i; }
    public int larger(IBox b){
        if(n > b.n)
            return n;
        else
            return b.n;
    }

    // ToIntBiFunction<T, U>    int applyAsInt(T t, U u)

    public static void main(String[] args){
        IBox ib1 = new IBox(5);
        IBox ib2 = new IBox(7);

        ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2);
        // ToIntBiFunction<IBox, IBox> bf = IBox::larger;

        int bigNum = bf.applyAsInt(ib1, ib2);

    }

}

```
```Java
// Optional

class ContInfo{
    String phone;
    String adrs;

    public ConstInfo(String ph, String ad){
        phone = ph;
        adrs = ad;
    }

    public String getPhone() { return phone; }
    public String getAdrs() { return adrs; }

    public static void main(String[] args){
        Optional<ContInfo> ci = Optional.of(new ContInfo(null, "Korea"));

        String phone = ci.map(c -> c.getPhone()).orElse("There is no phone number");
        String adrs = ci.map(c -> c.getAdrs()).orElse("There is no address");
    }
}
```
