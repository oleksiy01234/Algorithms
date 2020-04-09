import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class InsertDeleteGetRandomO1 {
  Map<Integer, Integer> map = new HashMap<>();
  List<Integer> list = new ArrayList<>();

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (map.containsKey(val)) {
      return false;
    }

    map.put(val, list.size());
    list.add(list.size(), val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }

    // move the last element to index of the element to delete
    int lastElement = list.get(list.size() - 1);
    int index = map.get(val);

    list.set(index, lastElement);
    map.put(lastElement, index);

    // delete the last element
    list.remove(list.size() - 1);
    map.remove(val);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return list.get(new Random().nextInt(list.size()));
  }
}