class OutOfStockException extends Exception
{
    private int shortfall;

    OutOfStockException(String item, int shortfall){
        super("Out of stock: " + item + " (short by " + shortfall + ")");
        this.shortfall = shortfall;
    }

    int getShortfall(){ return shortfall; }
}

class InvalidQuantityException extends Exception
{
    InvalidQuantityException(int qty){ super("Invalid quantity: " + qty); }
}

record Request(String item, int qty){}

class Warehouse
{
    private java.util.HashMap<String, Integer> stock = new java.util.HashMap<>();

    void add(String item, int qty){ stock.put(item, qty); }

    void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException
    {
        if(qty < 0){
            throw new InvalidQuantityException(qty);
        }

        int available = stock.getOrDefault(item, 0);

        if(qty > available){
            throw new OutOfStockException(item, qty - available);
        }

        stock.put(item, available - qty);
    }

    int getStock(String item){ return stock.getOrDefault(item, 0); }
}

public class StockIssue {
    public static void main(String[] args){

        Warehouse w = new Warehouse();
        w.add("pen", 50);
        w.add("book", 10);

        Request[] requests = {new Request("pen", 20), new Request("book", 15), new Request("pen", -5), new Request("bag", 1), new Request("pen", 30)};

        for(Request r : requests){
            try{
                w.issue(r.item(), r.qty());
                System.out.println("Issued " + r.qty() + " " + r.item() + " | left: " + w.getStock(r.item()));
            }
            catch(OutOfStockException e){
                System.out.println(e.getMessage() + " | shortfall: " + e.getShortfall());
            }
            catch(InvalidQuantityException e){
                System.out.println(e.getMessage());
            }
        }
    }
}