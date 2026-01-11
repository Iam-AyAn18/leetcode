# 🎨 DSA Patterns & Quick Reference Guide

**Last Updated:** January 11, 2026

---

## 📚 14 Essential Coding Patterns

### ✅ 1. Prefix Sum Pattern (MASTERED)
**When to use:** Finding sum of subarrays in O(1) time, counting subarrays with specific properties

**Template:**
```
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, 1);  // Important: base case
int prefixSum = 0;

for (int num : nums) {
    prefixSum += num;
    // Check if (prefixSum - target) exists in map
    if (map.containsKey(prefixSum - target)) {
        count += map.get(prefixSum - target);
    }
    map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
}
```

**Problems Solved:**
- ✅ #560 - Subarray Sum Equals K
- ✅ Maximum Size Subarray Sum Equals K
- ✅ #1 - Two Sum (variant)

**Problems to Practice:**
- #523 - Continuous Subarray Sum
- #525 - Contiguous Array
- #930 - Binary Subarrays With Sum
- #974 - Subarray Sums Divisible by K

**Key Points:**
- Always initialize map with (0, 1) for edge cases
- Use HashMap to store (prefixSum → frequency)
- Pattern works for sum, difference, XOR operations
- Time: O(n), Space: O(n)

---

### ✅ 2. Sliding Window Pattern (MASTERED)
**When to use:** Finding subarrays/substrings with specific properties, optimization problems

**Template (Variable Size):**
```
int start = 0, maxLength = 0;
HashSet<Character> set = new HashSet<>();

for (int i = 0; i < s.length(); i++) {
    // Shrink window while condition violated
    while (set.contains(s.charAt(i))) {
        set.remove(s.charAt(start));
        start++;
    }
    // Expand window
    set.add(s.charAt(i));
    maxLength = Math.max(maxLength, i - start + 1);
}
```

**Template (Fixed Size K):**
```
int windowSum = 0;
for (int i = 0; i < k; i++) {
    windowSum += nums[i];
}
int maxSum = windowSum;

for (int i = k; i < nums.length; i++) {
    windowSum = windowSum - nums[i - k] + nums[i];
    maxSum = Math.max(maxSum, windowSum);
}
```

**Problems Solved:**
- ✅ #3 - Longest Substring Without Repeating Characters

**Problems to Practice:**
- #424 - Longest Repeating Character Replacement
- #76 - Minimum Window Substring ⭐ (Hard)
- #209 - Minimum Size Subarray Sum
- #567 - Permutation in String
- #438 - Find All Anagrams in String

**Key Points:**
- Two pointers: start (left) and end (right)
- Expand window by moving right pointer
- Shrink window by moving left pointer
- Use HashMap/HashSet to track window state
- Time: O(n), Space: O(k) where k is unique elements

---

### ⏳ 3. Two Pointers Pattern
**When to use:** Sorted arrays, palindromes, pair finding, removing elements

**Template (Opposite Direction):**
```
int left = 0, right = nums.length - 1;

while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) {
        // Found answer
        return new int[]{left, right};
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

**Template (Same Direction):**
```
int slow = 0;
for (int fast = 0; fast < nums.length; fast++) {
    if (condition) {
        nums[slow] = nums[fast];
        slow++;
    }
}
return slow;
```

**Problems to Practice:**
- #167 - Two Sum II (Sorted)
- #15 - 3Sum ⭐
- #11 - Container With Most Water
- #125 - Valid Palindrome
- #283 - Move Zeroes
- #26 - Remove Duplicates from Sorted Array

**Key Points:**
- Usually requires sorted array (or you sort it first)
- O(n) time, O(1) space
- Can be extended to 3-sum, 4-sum problems
- Useful for in-place modifications

---

### ⏳ 4. Fast & Slow Pointers (Floyd's Cycle Detection)
**When to use:** Detecting cycles, finding middle of linked list, palindrome checking

**Template:**
```
ListNode slow = head, fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;           // Move 1 step
    fast = fast.next.next;      // Move 2 steps
    
    if (slow == fast) {
        // Cycle detected
        return true;
    }
}
return false;
```

**Problems to Practice:**
- #141 - Linked List Cycle
- #142 - Linked List Cycle II
- #876 - Middle of Linked List
- #234 - Palindrome Linked List
- #202 - Happy Number

**Key Points:**
- Fast pointer moves 2x speed of slow pointer
- If cycle exists, they will meet
- To find cycle start: reset one pointer to head, move both 1 step
- Time: O(n), Space: O(1)

---

### ⏳ 5. Binary Search Pattern
**When to use:** Sorted arrays, finding boundaries, search space reduction

**Template (Classic):**
```
int left = 0, right = nums.length - 1;

while (left <= right) {
    int mid = left + (right - left) / 2;
    
    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
return -1;
```

**Template (Binary Search on Answer):**
```
int left = minPossible, right = maxPossible;
int result = -1;

while (left <= right) {
    int mid = left + (right - left) / 2;
    
    if (isPossible(mid)) {
        result = mid;
        right = mid - 1;  // Try to find smaller
    } else {
        left = mid + 1;
    }
}
return result;
```

**Problems Solved:**
- ✅ #4 - Median of Two Sorted Arrays (Advanced)

**Problems to Practice:**
- #704 - Binary Search
- #35 - Search Insert Position
- #33 - Search in Rotated Sorted Array
- #153 - Find Minimum in Rotated Sorted Array
- #875 - Koko Eating Bananas (BS on Answer)
- #410 - Split Array Largest Sum (BS on Answer)

**Key Points:**
- Always use `mid = left + (right - left) / 2` to avoid overflow
- Three variants: find exact, find first occurrence, find last occurrence
- BS on answer: when you can verify if answer is possible in O(n)
- Time: O(log n), Space: O(1)

---

### ⏳ 6. Tree DFS Pattern
**When to use:** Tree traversal, path problems, depth calculations

**Template (Recursive):**
```
public int dfs(TreeNode root) {
    if (root == null) return 0;
    
    int left = dfs(root.left);
    int right = dfs(root.right);
    
    // Process current node
    return Math.max(left, right) + 1;
}
```

**Template (Iterative with Stack):**
```
Stack<TreeNode> stack = new Stack<>();
stack.push(root);

while (!stack.isEmpty()) {
    TreeNode node = stack.pop();
    // Process node
    
    if (node.right != null) stack.push(node.right);
    if (node.left != null) stack.push(node.left);
}
```

**Problems Solved:**
- ✅ #1339 - Maximum Product of Splitted Binary Tree

**Problems to Practice:**
- #104 - Maximum Depth of Binary Tree
- #110 - Balanced Binary Tree
- #543 - Diameter of Binary Tree
- #236 - Lowest Common Ancestor
- #124 - Binary Tree Maximum Path Sum ⭐

**Key Points:**
- Three traversals: Preorder (Root-Left-Right), Inorder (Left-Root-Right), Postorder (Left-Right-Root)
- Use global variable for complex calculations
- Time: O(n), Space: O(h) where h is height

---

### ⏳ 7. Tree BFS Pattern
**When to use:** Level-order traversal, shortest path in tree, level-based calculations

**Template:**
```
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
    int levelSize = queue.size();
    
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        // Process node
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
    // Process level
}
```

**Problems Solved:**
- ✅ #1161 - Maximum Level Sum of Binary Tree

**Problems to Practice:**
- #102 - Binary Tree Level Order Traversal
- #103 - Binary Tree Zigzag Level Order
- #199 - Binary Tree Right Side View
- #637 - Average of Levels in Binary Tree

**Key Points:**
- Use queue for FIFO processing
- Track level size to process level by level
- Time: O(n), Space: O(w) where w is max width

---

### ⏳ 8. Graph DFS/BFS Pattern
**When to use:** Island problems, connectivity, path finding

**Template (DFS):**
```
boolean[][] visited = new boolean[m][n];
int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

void dfs(int row, int col) {
    if (row < 0 || row >= m || col < 0 || col >= n || 
        visited[row][col] || grid[row][col] == '0') {
        return;
    }
    
    visited[row][col] = true;
    
    for (int[] dir : directions) {
        dfs(row + dir[0], col + dir[1]);
    }
}
```

**Problems to Practice:**
- #200 - Number of Islands ⭐
- #695 - Max Area of Island
- #130 - Surrounded Regions
- #417 - Pacific Atlantic Water Flow
- #994 - Rotting Oranges (BFS)

**Key Points:**
- Use visited array to avoid revisiting
- DFS for connected components, BFS for shortest path
- Time: O(V + E), Space: O(V)

---

### ⏳ 9. Dynamic Programming - 1D
**When to use:** Optimization problems with overlapping subproblems

**Template (Bottom-Up):**
```
int[] dp = new int[n + 1];
dp[0] = baseCase1;
dp[1] = baseCase2;

for (int i = 2; i <= n; i++) {
    dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
}
return dp[n];
```

**Template (Top-Down with Memoization):**
```
Integer[] memo = new Integer[n + 1];

int dp(int n) {
    if (n <= 1) return n;
    if (memo[n] != null) return memo[n];
    
    memo[n] = dp(n-1) + dp(n-2);
    return memo[n];
}
```

**Problems Solved:**
- ✅ Fibonacci

**Problems to Practice:**
- #70 - Climbing Stairs
- #198 - House Robber ⭐
- #213 - House Robber II
- #322 - Coin Change ⭐
- #300 - Longest Increasing Subsequence

**Key Points:**
- Identify state (what changes)
- Define recurrence relation
- Choose between top-down (easier) or bottom-up (faster)
- Time: O(n), Space: O(n) or O(1) with optimization

---

### ⏳ 10. Dynamic Programming - 2D
**When to use:** Two sequences comparison, grid problems, knapsack variants

**Template:**
```
int[][] dp = new int[m + 1][n + 1];

// Base cases
for (int i = 0; i <= m; i++) dp[i][0] = 0;
for (int j = 0; j <= n; j++) dp[0][j] = 0;

// Fill table
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (condition) {
            dp[i][j] = dp[i-1][j-1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }
}
return dp[m][n];
```

**Problems to Practice:**
- #1143 - Longest Common Subsequence ⭐
- #72 - Edit Distance ⭐
- #5 - Longest Palindromic Substring
- #1458 - Max Dot Product of Two Subsequences
- #221 - Maximal Square

**Key Points:**
- State: dp[i][j] represents solution for first i elements of seq1, first j elements of seq2
- Time: O(m×n), Space: O(m×n) or O(n) with optimization
- Can optimize space by keeping only previous row

---

### ⏳ 11. Monotonic Stack
**When to use:** Next greater/smaller element, histogram problems

**Template (Next Greater Element):**
```
Stack<Integer> stack = new Stack<>();
int[] result = new int[nums.length];

for (int i = nums.length - 1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() <= nums[i]) {
        stack.pop();
    }
    result[i] = stack.isEmpty() ? -1 : stack.peek();
    stack.push(nums[i]);
}
```

**Problems Solved:**
- ✅ #85 - Maximal Rectangle (uses this concept)

**Problems to Practice:**
- #739 - Daily Temperatures ⭐
- #496 - Next Greater Element I
- #84 - Largest Rectangle in Histogram ⭐
- #42 - Trapping Rain Water

**Key Points:**
- Stack maintains increasing or decreasing order
- Pop elements that violate monotonic property
- Time: O(n), Space: O(n)

---

### ⏳ 12. Backtracking
**When to use:** Generate all possibilities, permutations, combinations, subsets

**Template:**
```
List<List<Integer>> result = new ArrayList<>();

void backtrack(List<Integer> current, int start) {
    // Base case
    if (condition) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = start; i < nums.length; i++) {
        // Make choice
        current.add(nums[i]);
        
        // Recurse
        backtrack(current, i + 1);
        
        // Undo choice (backtrack)
        current.remove(current.size() - 1);
    }
}
```

**Problems to Practice:**
- #78 - Subsets ⭐
- #46 - Permutations ⭐
- #39 - Combination Sum
- #79 - Word Search
- #51 - N-Queens

**Key Points:**
- Three steps: Choose, Explore, Unchoose
- Time: Often O(2^n) or O(n!)
- Use pruning to optimize

---

### ⏳ 13. Heap / Priority Queue
**When to use:** K-th largest/smallest, merge sorted sequences, median finding

**Template:**
```
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// Add elements
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();  // Remove smallest
    }
}

return minHeap.peek();  // K-th largest
```

**Problems to Practice:**
- #215 - Kth Largest Element in Array
- #347 - Top K Frequent Elements
- #23 - Merge K Sorted Lists
- #295 - Find Median from Data Stream ⭐

**Key Points:**
- Min heap: smallest element at top
- Max heap: largest element at top
- Operations: O(log n) for insert/remove, O(1) for peek
- Time: O(n log k) for k-th element problems

---

### ⏳ 14. Union Find (Disjoint Set)
**When to use:** Dynamic connectivity, cycle detection, grouping

**Template:**
```
class UnionFind {
    int[] parent, rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
    
    public boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;
        
        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}
```

**Problems to Practice:**
- #684 - Redundant Connection
- #547 - Number of Provinces
- #721 - Accounts Merge
- #128 - Longest Consecutive Sequence (can use Union Find)

**Key Points:**
- Two optimizations: Path compression + Union by rank
- Near constant time operations: O(α(n)) where α is inverse Ackermann
- Used for grouping and connectivity problems

---

## 🎯 Pattern Recognition Guide

### "Find subarray with sum/properties" → Prefix Sum or Sliding Window
### "Find pair/triplet with sum" → Two Pointers (if sorted) or HashMap
### "Detect cycle in linked list" → Fast & Slow Pointers
### "Find in sorted array" → Binary Search
### "Tree path/depth problem" → DFS
### "Tree level problem" → BFS
### "Connected components" → Graph DFS/BFS or Union Find
### "Optimization problem" → Dynamic Programming or Greedy
### "Generate all possibilities" → Backtracking
### "Next greater/smaller" → Monotonic Stack
### "K-th largest/smallest" → Heap
### "Interval merging" → Sort + Merge
### "In-place array modification" → Two Pointers (same direction)

---

## 📊 Complexity Cheat Sheet

| Data Structure | Access | Search | Insert | Delete | Space |
|---------------|--------|--------|---------|---------|-------|
| Array | O(1) | O(n) | O(n) | O(n) | O(n) |
| Dynamic Array | O(1) | O(n) | O(1)* | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1) | O(1) | O(n) |
| Stack | O(n) | O(n) | O(1) | O(1) | O(n) |
| Queue | O(n) | O(n) | O(1) | O(1) | O(n) |
| Hash Table | N/A | O(1)* | O(1)* | O(1)* | O(n) |
| Binary Search Tree | O(log n)* | O(log n)* | O(log n)* | O(log n)* | O(n) |
| Heap | N/A | O(n) | O(log n) | O(log n) | O(n) |
| Trie | N/A | O(k) | O(k) | O(k) | O(n×k) |

\* Average case (amortized or with good hash function)

| Algorithm | Time | Space |
|-----------|------|-------|
| Binary Search | O(log n) | O(1) |
| Merge Sort | O(n log n) | O(n) |
| Quick Sort | O(n log n)* | O(log n) |
| Heap Sort | O(n log n) | O(1) |
| DFS | O(V + E) | O(V) |
| BFS | O(V + E) | O(V) |
| Dijkstra | O((V + E) log V) | O(V) |
| Floyd-Warshall | O(V³) | O(V²) |
| Union Find | O(α(n))* | O(n) |
| Kadane's Algorithm | O(n) | O(1) |

---

## 🔑 Key Formulas & Math

### Common Math:
- **Sum of 1 to n:** n(n+1)/2
- **Sum of squares:** n(n+1)(2n+1)/6
- **Power of 2 check:** (n & (n-1)) == 0
- **Count set bits:** Integer.bitCount(n)
- **XOR properties:** a ^ a = 0, a ^ 0 = a
- **Modulo:** (a + b) % m = ((a % m) + (b % m)) % m
- **GCD:** Euclidean algorithm - gcd(a,b) = gcd(b, a%b)
- **Prime check:** Check divisors up to √n

### Bit Manipulation:
- **Get bit:** (num >> i) & 1
- **Set bit:** num | (1 << i)
- **Clear bit:** num & ~(1 << i)
- **Toggle bit:** num ^ (1 << i)
- **Clear rightmost 1:** num & (num - 1)
- **Isolate rightmost 1:** num & (-num)

---

## 🎓 Problem-Solving Checklist

Before coding:
- [ ] Do I understand all requirements and constraints?
- [ ] What are the edge cases?
- [ ] Can I solve it with brute force first?
- [ ] Which pattern does this match?
- [ ] What's the optimal time/space complexity?

While coding:
- [ ] Am I handling all edge cases?
- [ ] Are my variable names clear?
- [ ] Is my code clean and readable?
- [ ] Am I avoiding common mistakes (off-by-one, overflow, etc.)?

After coding:
- [ ] Did I test with the examples?
- [ ] Did I test edge cases?
- [ ] Can I optimize further?
- [ ] Can I explain my solution clearly?
- [ ] What did I learn from this problem?

---

**Last Updated:** January 11, 2026

