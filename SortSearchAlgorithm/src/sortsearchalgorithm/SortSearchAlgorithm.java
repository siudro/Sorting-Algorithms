package sortsearchalgorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class SortSearchAlgorithm {

    public static void main(String[] args) {
        
        String[] Names = {"YAHYA", "HASAN", "EID", "MANAL", "BDOOR", "RAWIAH", "HAIFA", "AMJAD", "FAHAD", "OHOOD", "MAHA", "SARA", "AMAL", "MEHAD", "ZAIN"};
        Integer[] ID = {3692098, 3692102, 3692104, 3692107, 3692112, 3692256, 3692449, 3692643, 3693199, 3757224, 3757225, 3757622, 3757623, 3757625, 3757628, 3757629, 3757631};
        Integer[]a ={7,11,4,2,9,3};

        System.out.println("Old linear search: ");
        System.out.println(linearSearch(Names, 0, Names.length, "AMAL") ? "Found!" : "Not Found!");
        System.out.println(linearSearch(ID, 0, ID.length, 3692107) ? "Found!" : "Not Found!");
        System.out.println(binarySearch(ID, 0, ID.length, 3692107) ? "Found!" : "Not Found!");
        System.out.println(binarySearch(ID, 0, ID.length, 3692999) ? "Found!" : "Not Found!");
        System.out.println("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــ");

        System.out.println("new linear search: ");
        System.out.println(linearSearchNew(Names, 0, Names.length, "AMAL") ? "Found!" : "Not Found!");
        System.out.println(linearSearchNew(ID, 0, ID.length, 3692107) ? "Found!" : "Not Found!");
        System.out.println(binarySearch(ID, 0, ID.length, 3692107) ? "Found!" : "Not Found!");
        System.out.println(binarySearch(ID, 0, ID.length, 3692999) ? "Found!" : "Not Found!");
        System.out.println("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــ");

        System.out.println("before32 sorting: ");
        System.out.println("ID:");
        print(ID);
        System.out.println("Names:");
        print(Names);
        System.out.println("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــ");

        bubbleSort(Names, Names.length);
        bubbleSort(a, a.length);
        System.out.println("after bubble sorting: ");
        System.out.println("a:");
        print(a);
        System.out.println("Names:");
        print(Names);
        System.out.println("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
        System.out.println("after selection sorting: ");

        selectionSort(ID);
        System.out.println("ID:");
        print(ID);
        System.out.println("Names:");
        print(Names);
        System.out.println("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
    }

    public static <T> boolean linearSearch(T[] data, int min, int max, T target) {
        int index = min;
        boolean found = false;
        while (!found && index <= max) {
            found = data[index].equals(target);
            index++;
        }
        return found;
    }

    public static <T extends Comparable<T>> boolean linearSearchNew(T[] data, int min, int max, T target) {
        int index = min;
        boolean found = false;
        while (!found && index <= max) {
            if (data[index].compareTo(target) > 0) {
                return false;
            }
            found = data[index].equals(target);
            index++;
        }
        return found;
        // نضيف شرط يسوي مقارنة كل اندكس مع التارقت، اذا طلع 1 وقتها يرجع فولس 
    }

    public static <T extends Comparable<T>> boolean binarySearch(T[] data, int min, int max, T target) {
        boolean found = false;
        int midpoint = (min + max) / 2; // determine the midpoint
        if (data[midpoint].compareTo(target) == 0) {
            found = true;
        } else if (data[midpoint].compareTo(target) > 0) {
            if (min <= midpoint - 1) {
                found = binarySearch(data, min, midpoint - 1, target);
            }
        } else if (midpoint + 1 <= max) {
            found = binarySearch(data, midpoint + 1, max, target);
        }
        return found;
    }

    public static <T extends Comparable<T>> void bubbleSort(T a[], int length) {
        for (int i = 0; i < (length - 1); i++) {
            for (int j = length - 1; j > i; j--) {
                if (a[j - 1].compareTo(a[j]) > 0) {
                    T temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] data) {
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++) {
                if (data[scan].compareTo(data[min]) < 0) {
                    min = scan;
                }
            }
            swap(data, min, index);
        }

    }

    public static <T extends Comparable<T>> void swap(T[] data, int index1, int index2) {
        T temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    public static <T extends Comparable<T>> void insertionSort(T[] data) {
        for (int index = 1; index < data.length; index++) {
            T key = data[index];
            int position = index;
            while (position > 0 && data[position - 1].compareTo(key) > 0) {
                data[position] = data[position - 1];
                position--;
            }
            data[position] = key;
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] data, int min,
            int max) {
        if (min < max) {
// create partitions 
            int indexofpartition = partition(data, min, max);
// sort the left partition (lower values) 
            quickSort(data, min, indexofpartition - 1);
// sort the right partition (higher values) 
            quickSort(data, indexofpartition + 1, max);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] data, int min,
            int max) {
        T element;
        int left, right;
        int middle = (min + max) / 2;
        swap(data, middle, min);
        left = min;
        right = max;
        while (left < right) {
            while (left < right && data[left].compareTo(element)
                    <= 0) {
                left++;
                while (data[right].compareTo(element) > 0) {
                    right--;
                }
                if (left < right) {
                    swap(data, left, right);
                }
            }
            swap(data, min, right);
            return right;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] data, int min,
            int max) {
        if (min < max) {
            int mid = (min + max) / 2;
            mergeSort(data, min, mid);
            mergeSort(data, mid + 1, max);
            merge(data, min, mid, max);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] data, int first, int mid, int last) {
        T[] temp = (T[]) (new Comparable[data.length]);
        int first1 = first, last1 = mid;
        int first2 = mid + 1, last2 = last;
        int index = first1; 
        while (first1 <= last1 && first2 <= last2) {
            if (data[first1].compareTo(data[first2]) < 0) {
                temp[index] = data[first1];
                first1++;
            } else {
                temp[index] = data[first2];
                first2++;
            }
            index++;
        }
        while (first1 <= last1) {
            temp[index] = data[first1];
            first1++;
            index++;
        }
        while (first2 <= last2) {
            temp[index] = data[first2];
            first2++;
            index++;
        }
        for (index = first; index <= last; index++) {
            data[index] = temp[index];
        }
    }

    public static <T extends Comparable<T>> void print(T[] list) {
        for (T i : list) {
            System.out.print(i + ",");
        }
        System.out.println("");
    }
}
