(ns section1_4
  (:require
    [clojure.set :as set]))

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

;; pulling values out is done with `get`:
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

;; We'll do more with these kinds of functions later, and you'll read more about them in sections.

;; Clojure actually allows you to use Java things. This is one of the coolest things about the choice of
;; underlying platform, as Java has a HUGE number of useful things already built for it. You do not need
;; to know how to write Java, but you will occasionally want to use things from it. We'll be learning
;; these details later, and you need not learn very much right now. The main point of this little section is
;; to give you a little insight into the trade-offs of Clojure's immutable data structures.

;; EXERCISE: Run the following five things. Some use Java's mutable HashMap (we briefly mentioned in
;; the book), and some use Clojure's maps. Run them a few times...the times will be a little erratic due to the nature
;; of modern computing.

;; Read a clojure map 1 million times
(let [m {:a 1 :b 2 :c 3 :d 4}]
  (time
    (dotimes [n 1000000]
      (get m :c))))

;; Read a Java map 1 million times
(let [jm (java.util.HashMap.)]
  ;; In Java this is jm.put(clojure.lang.Keyword("b"), 1), but Clojure
  ;; has the keywords natively, and it needs to move the paren and the operation (.put) to the front.
  ;; Think of it like English vs German grammar...sometimes the subject (jm) and verb (put) have a
  ;; different order.
  (.put jm :a 1)
  (.put jm :b 1)
  (.put jm :c 1)
  (.put jm :d 1)
  (time
    (dotimes [n 1000000]
      (.get jm :c))))

;; Build up 1 million entries one at a time
(let [m {}]
  (time
    (do
      (reduce
        (fn [acc n]
          (assoc acc n n))
        {}
        (range 1000000))
      nil)))

;; In Clojure, there's actually a way to speed this up a bit using a thing called "transients"
(let [m {}]
  (time
    (do
      (persistent!
        (reduce
          (fn [acc n]
            (assoc! acc n n))
          (transient {})
          (range 1000000)))
      nil)))

;; But in Java it's still just faster (and there are high-performance specialized collections that
;; are faster still). But either way you can *use* them from Clojure when it matters!
(let [jm (java.util.HashMap.)]
  (time
    (dotimes [n 1000000]
      (.put jm n n))))

;; Sets

;; In general sets are the least used data structure, but they're still important. If you look at the
;; top of this file, you'll see we required (a.k.a. loaded) a namespace that is built-in to Clojure
;; and gave it the alias `set` (that's what the :as means).

;; Simple sets can just be types as in the book:
#{1 2 :a :b}

;; EXERCISE: Type out the set above, but try putting :a in it twice. What happens when you try to
;; run that in the REPL?

;; Mostly you just want to do three main things with sets: Put things in them, take things out, and
;; see if something is already in them. The functions for this are conj, disj, and contains?
(conj #{1 2 3} 4)
(disj #{1 2 3} 2)
(contains? #{1 2 3} 1)
(contains? #{1 2 3} 0)

;; More advanced operations are pre-defined in that clojure.set namespace. They include the common
;; mathematical operations (which we prefix with `set/` because that's what our require at the top
;; of this file said we'd call it):

(set/union #{:a :d} #{:a :b :c})
(set/difference #{:a :d} #{:a :b :c})
(set/intersection #{:a :c :d} #{:a :b :c})
(set/subset? #{:a :b :c} #{:a})
(set/subset? #{:a} #{:a :b :c})
(set/superset? #{:a} #{:a :b :c})
(set/superset? #{:a :b :c} #{:a})

;; You can convert regular sequences (lists and vectors) into sets
(set [1 2 3])
(set (list 1 2 3))
;; and for that matter you can go the other way:
(vec #{1 2 3})

;; QUESTION: Was anything surprising about the conversion from set to vector? Can you explain it?

;; Say you had two dating app members whose profiles had sets for what they are seeking:
(def seeking1 #{:dating :friends})
(def seeking2 #{:long-term :friends :hook-ups})

;; EXERCISE: Write an expression using the things you just learned that would tell you which things they are
;; both seeking:

;; Vectors

;; Vectors themselves are rather simple, and can use many of the same functions you've already learned:
;; Assoc takes an OFFSET for vectors
(assoc [1 2 3 4] 1 :X)

;; EXERCISE: Change the 1 before the :X to various values. What kinds of
;; things do you discover? (e.g. use 0, 3, or 5).

(get [:a :b :c] 0)

;; EXERCISE: Change the 0 above to other numbers. Does this have the same behavior as assoc?

; You can also use the generic sequence functions:
(first [1 2 3 4 5])
(second [1 2 3 4 5])
(last [1 2 3 4 5])
(rest [1 2 3 4 5])
(butlast [1 2 3 4 5])
(take 3 [1 2 3 4 5])
(drop 2 [1 2 3 4 5])
(reverse [1 2 3])
(shuffle [1 2 3 4 5])

;; The `nth` function is a lot like `get`.
(nth [:a :b :c :d] 3)

;; EXERCISE: What happens if you change the 3 to various numbers in `nth`?

;; However, a complication we've not discussed yet is that *many* of the functions that work on
;; vectors do not *result* in vectors!  Re-run the examples above like `rest` and notice that the
;; output is a list (technically it is even weirder than that, but don't worry about that bit yet).

;; This means that sometimes you can end up with surprising results when you do a sequence of things
;; For example, `get` only works on things that can be accessed by index. Vectors can, but these
;; returned lists cannot:
(get (reverse [1 2 3 4]) 3)

;; As a result there are sometimes variants of functions that purposely build a vector as their
;; result. These are usually suffixed with `v`:
(map inc [1 2 3])
(mapv inc [1 2 3])

;; but not very many functions have such a variant.

;; One recourse is to convert them yourself using `vec` as a wrapper:
(get (vec (reverse [1 2 3 4])) 0)
