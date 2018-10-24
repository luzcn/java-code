package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// Given a list accounts, each element accounts[i] is a list of strings,
// where the first element accounts[i][0] is a name,
// and the rest of the elements are emails representing emails of the account.
//
// Now, we would like to merge these accounts.
// Two accounts definitely belong to the same person if there is some email that is common to both accounts.
//
// Note that even if two accounts have the same name,
// they may belong to different people as people could have the same name.
//
// A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
// After merging the accounts, return the accounts in the following format:
// - the first element of each account is the name,
// - and the rest of the elements are emails in sorted order.
//
// The accounts themselves can be returned in any order.
public class AccountsMerge_721 {

  public int[] id;

  // get the root
  private int root(int i) {
    while (this.id[i] != i) {
      i = this.id[i];
    }

    return i;
  }

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    int n = accounts.size();

    this.id = new int[n];
    for (int i = 0; i < n; i++) {
      this.id[i] = i;
    }

    List<List<String>> result = new ArrayList<>();
    HashMap<String, Integer> mapEmailToID = new HashMap<>();
    HashMap<Integer, TreeSet<String>> mapIdToEmail = new HashMap<>();

    // process
    // now, each account history item has an id
    // - for each email, put the <email,id> into a hash map
    // - if we found one email already in the hash map, we unite these two account,
    // e.g. root(account2.id) = root(map.get(email))
    for (int index = 0; index < accounts.size(); index++) {
      List<String> account = accounts.get(index);

      for (int j = 1; j < account.size(); j++) {
        String email = account.get(j);

        if (!mapEmailToID.containsKey(email)) {
          // if the email is not in the map,
          // add the email with the root value
          mapEmailToID.put(email, root(index));
        } else {
          // update the id
          id[root(index)] = root(mapEmailToID.get(email));
        }
      }
    }

    // now, in the id[] array, any items have the same root(index), they are in the same set
    for (int index = 0; index < accounts.size(); index++) {
      List<String> account = accounts.get(index);

      for (int j = 1; j < account.size(); j++) {
        mapIdToEmail.computeIfAbsent(root(index), k -> new TreeSet<>()).add(account.get(j));
      }
    }

    // iterate through the mapDiToEmail,
    // merge all the emails with the same id and the corresponding name.
    for (Map.Entry<Integer, TreeSet<String>> entry : mapIdToEmail.entrySet()) {
      int id = root(entry.getKey());
      String name = accounts.get(id).get(0);

      List<String> account = new ArrayList<>();
      account.add(name);
      account.addAll(entry.getValue());

      result.add(account);
    }

    return result;
  }
}
