(ns section1_4)

;; See the instructions in section1_3 about fetching book changes, and do that first!

;; Make sure you complete (and run in the REPL) each step. Later steps rely on values defined earlier in the file!

;; Lists

;; The chapter described some ways to create and add to lists
(def list1 (list 1 2 3))
(def list2 (cons 10 list1))

;; EXERCISE: Guess the content of list2 and write your answer below, then use the REPL to check your answer.

;; The `concat` function takes lists, and returns a new list that is the combination of the others:
(def list3 (concat list1 list2))

;; EXERCISE: Guess the content of `list3` and write it below. Then use the REPL to check your answer.

;; You can access portions of any sequence in clojure (lists included) using a few handy functions.
;; If you want the first item in a list, use `first`

;; EXERCISE: Write the expression that will give you the first item for list1.

;; You can also get some "portion" of a list using `take` and `drop`. The former gives you N nodes, and the latter
;; skips N nodes. For example, guess what these would return and then run them:
(take 2 list2)
(drop 2 list1)

;; So, say you wanted the 3rd item from a linked list. You would first drop 2, then use `first`.
(first (drop 2 list1))

;; QUESTION: If you had a list of 1000 items and want item 999, what are the performance characteristics for using
;; `drop` and `first` this way?

;; EXERCISE: The `last` function returns the last item in a sequence. Use `take` and `last` to get the 2nd item from list1

;; Maps

;; The importance of maps in Clojure cannot be understated. They are used to represent the vast majority of data in
;; our programs, and since keywords are often used as the keys, one can also say that keywords also have a very
;; important role.

;; It is important you know some of the primary ways of working with maps. As the chapter explained, maps are immutable,
;; which means every modification you want to make is really the creation of a new map which shares some space with the
;; old one (structural sharing).  The chapter mentioned `assoc` as a way to add key-value (kv) pairs:
(assoc {:x 1} :y 2 :z 3)

;; EXERCISE: Use def on the prior statement to make the result of that assoc known as `m1`

;; and pulling values out is done with `get`:
(get {:x 1 :y 2} :x)

;; Remember from the chapter that the key and value of a map can really be anything, including other maps!
(def joe {:person/name    "Joe"
          :person/address {:address/street "123 main"}})

;; Pulling data from such a map can be tedious if all you had was `get`!
(get (get joe :person/address) :address/street)

;; So, there is a better function for this called `get-in`, which takes a path to the desired value (as a vector):
(get-in joe [:person/address :address/street])

;; EXERCISE: Make a `def` like joe above, but invent your own name and make up a nested map with some depth
;; (go as deep as you want). Use get-in to pull various values.

;; It turns out that there is a similar function for putting things IN at a given path called `assoc-in`.
(assoc-in {} [:x :y :z] 42)

;; EXERCISE: Use `assoc-in` to make a new version of `joe` (i.e. `(assoc-in joe ...)`) where his address includes the
;; city "New York".

;; Functional programming languages allow you to leverage functions (the operations we've been using in all the exercises,
;; like `+`, `*`, `get`, etc.) in interesting ways. This gets interesting with maps because you often want to do
;; some *operation* to the data in a map. This is the purpose of `update`. The `update` function takes a map, the key
;; to affect, and an *operation* to do at that key.

;; For example, the `inc` function increments a number by one:
(inc 2)
(inc 52)

;; so say we had this coordinate:
(def point {:x 125 :y 200})

;; and we wanted a new version of the point where x was increased by one. We would use update like this:
(update point :x inc)

;; You can pass additional argument to update, and they will be added to the operation's arguments. For example:
(update point :y + 22)

;; EXERCISE: Use update to triple the value of `:x` in `point`
