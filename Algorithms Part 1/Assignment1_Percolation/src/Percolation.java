import java.util.ArrayList;

public class Percolation {
    ArrayList<ArrayList<Tuple>> Id = new ArrayList<>();
    ArrayList<ArrayList<Integer>> Size = new ArrayList<>();
    int count;
    int LENGTH;
    // 0 means Close, 1 means Open, (-1,-1) means Full

    public Percolation(int n) {                     // create n-by-n grid, with all sites blocked
        for (int i = 0; i < n; i++) {
            ArrayList<Tuple> row1 = new ArrayList<>();
            ArrayList<Integer> row2 = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                Tuple a = new Tuple(i, j);
                row1.add(a);
                row2.add(0);
            }
            Id.add(row1);
            Size.add(row2);
        }
        count = 0;
        LENGTH = n;
//        System.out.println("DONE");
    }

    public void open(int row, int col) {        // open site (row, col) if it is not open already
        if (row > 0 && row <= LENGTH && col > 0 && col <= LENGTH && !isOpen(row, col)) {
//            System.out.println("OPENING");
            count += 1;
            ArrayList<Tuple> IdRow = Id.get(row - 1);
            ArrayList<Integer> SizeRow = Size.get(row - 1);
            Integer s = 0;
            Integer check;
            Tuple a = new Tuple(row - 1, col - 1);
            if (row == 1) {
//                System.out.println("case 1");
                IdRow.set(col - 1, new Tuple(-1, -1));
                SizeRow.set(col - 1, 1);
                Id.set(row - 1, IdRow);
                Size.set(row - 1, SizeRow);
                if (isOpen(row + 1, col) && !isFull(row + 1, col)) {
//                    System.out.println("Check");
                    a = Id.get(row).get(col - 1);
                    while (Size.get(a.first).get(a.last) != 1) {
//                        System.out.println("LOOPING");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(-1, -1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
            }
            else if (isFull(row, col - 1) || isFull(row, col + 1) || isFull(row - 1, col) || isFull(row + 1, col)) {
//                System.out.println("case 2");
                IdRow.set(col - 1, new Tuple(-1, -1));
                SizeRow.set(col - 1, 1);
                Id.set(row - 1, IdRow);
                Size.set(row - 1, SizeRow);
                if (isOpen(row + 1, col) && !isFull(row + 1, col)) {
//                    System.out.println("Check");
                    a = Id.get(row).get(col - 1);
                    while (Size.get(a.first).get(a.last) != 1) {
//                        System.out.println("LOOPING");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(-1, -1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row - 1, col) && !isFull(row - 1, col)) {
//                    System.out.println("Check");
                    a = Id.get(row - 2).get(col - 1);
                    while (Size.get(a.first).get(a.last) != 1) {
//                        System.out.println("LOOPING");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(-1, -1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row, col + 1) && !isFull(row, col + 1)) {
//                    System.out.println("Check");
                    a = Id.get(row - 1).get(col);
                    while (Size.get(a.first).get(a.last) != 1) {
//                        System.out.println("LOOPING");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(-1, -1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row, col - 1) && !isFull(row, col - 1)) {
//                    System.out.println("Check");
                    a = Id.get(row - 1).get(col - 2);
                    while (Size.get(a.first).get(a.last) != 1 && a.first != row - 1 && a.last != col - 1) {
//                        System.out.println("LOOPING");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(-1, -1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
            } else {
//                System.out.println("case 3");
                if (isOpen(row, col - 1)) {
                    a = Id.get(row - 1).get(col - 2);
                    while (Size.get(a.first).get(a.last) != 1 && a.first != row - 1 && a.last != col - 1) {
//                        System.out.println("LOOPING1");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
//                        System.out.println("LOOPING1 " + a.first + ", " + a.last + " " + currentSize);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(row - 1, col - 1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row, col + 1)) {
                    a = Id.get(row - 1).get(col);
                    while (Size.get(a.first).get(a.last) != 1 && (a.first != row - 1 || a.last != col - 1)) {
//                        System.out.println("LOOPING2");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
//                        System.out.println("LOOPING2 " + a.first + ", " + a.last + " " + currentSize);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(row - 1, col - 1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row + 1, col)) {
                    a = Id.get(row).get(col - 1);
                    while (Size.get(a.first).get(a.last) != 1 && (a.first != row - 1 || a.last != col - 1)) {
//                        System.out.println("LOOPING3");
//                        System.out.println(a.first + " " + a.last);
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
//                        System.out.println("LOOPING3 " + a.first + ", " + a.last + " " + currentSize);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(row - 1, col - 1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                if (isOpen(row - 1, col)) {
                    a = Id.get(row - 2).get(col - 1);
                    while (Size.get(a.first).get(a.last) != 1 && (a.first != row - 1 || a.last != col - 1)) {
//                        System.out.println("LOOPING4");
                        Integer currentSize = Size.get(a.first).get(a.last);
                        ArrayList<Integer> currentSizeRow = Size.get(a.first);
                        currentSizeRow.set(a.last, currentSize + 1);
//                        System.out.println("LOOPING4 " + a.first + ", " + a.last + " " + currentSize);
                        Size.set(a.first, currentSizeRow);
                        a = Id.get(a.first).get(a.last);
                    }
                    IdRow = Id.get(a.first);
                    SizeRow = Size.get(a.first);
                    IdRow.set(a.last, new Tuple(row - 1, col - 1));
                    SizeRow.set(a.last, 2);
                    Size.set(a.first, SizeRow);
                    Id.set(a.first, IdRow);
                }
                SizeRow = Size.get(row - 1);
                SizeRow.set(col - 1, 1);
                Size.set(row - 1, SizeRow);
            }
//            System.out.println(Size.get(row - 1).get(col - 1));
        }
    }

    public boolean isOpen(int row, int col) {   // is site (row, col) open?
        if (row > 0 && row <= LENGTH && col > 0 && col <= LENGTH) {
//            System.out.println("isOpen" + " " + row + " " + col);
            Integer s = Size.get(row - 1).get(col - 1);
            if (s > 0) {
//                System.out.println("isOpen" + " " + row + " " + col);
                return true;
            }
        }
        return false;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (row > 0 && row <= LENGTH && col > 0 && col <= LENGTH && isOpen(row, col)) {
//            System.out.println("isFull" + " " + row + " " + col);
            Tuple a = Id.get(row - 1).get(col - 1);
            Integer s = Size.get(row - 1).get(col - 1);
            while (s >= 1) {
                if (a.first == -1 && a.last == -1) {
//                    System.out.println("isFull" + " " + row + " " + col);
                    return true;
                }
                s = Size.get(a.first).get(a.last);
                a = Id.get(a.first).get(a.last);
                if (s == 1) {
                    return false;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {      // number of open sites
        return count;
    }

    public boolean percolates() {             // does the system percolate?
        for (int i = 0; i < LENGTH; i++) {
            if (isFull(LENGTH, i + 1)) {
//                System.out.println("PERCOLATES");
                return true;
            }
        }
//        System.out.println("NOT PERCOLATES");
        return false;
    }

    public static void main(String[] args) {  // test client (optional)

    }

    public class Tuple {
        Integer first;
        Integer last;
        public Tuple (int x, int y) {
            this.first = x;
            this.last = y;
        }
    }
}
