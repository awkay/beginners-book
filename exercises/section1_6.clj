(ns section1_6
  "Making Things Happen.")

;; EXERCISE: Look up the formula for converting from Fahrenheit to Celcius. Write a function that takes F as input, and outputs the temp in C.
;; be sure to test it on some values that you know the answer for, like boiling point and freezing. Name the function `f-to-c`


;; EXERCISE: Just like prior, but in reverse: Write a function for C -> F, called `c-to-f`, and
;; be sure to test it on some values that you know the answer for, like boiling point and freezing.


;; The `if` expression in Clojure takes a boolean expression, and a "then" and "else" expression. Without the "else" it
;; will result in `nil`. Try out the following and play with them to make sure you understand:

(if (> 10 5)
  "10 is bigger than 5"
  "unreachable expression")

(if (= :a :a)
  1
  2)

;; There is a variant for when you don't have an "else" called `when`:
(when (= 1 2)
  "NO!")

(when (= 1 1)
  "Yay!")


;; EXERCISE: Write a function that takes a single number as input, and returns true if the number greater than 1000. Call your function `large-number?`

;; EXERCISE: The `filter` function takes a predicate function and a sequence. A predicate function is ANY function that
;; accepts ONE argument and returns true/false. So, your `large-number?` is such a function.
;; Guess the output of the following,  and then uncomment it and run it.

;; (filter large-number? [1 400 999 1000 1001 9999])

;; EXERCISE: In the filter above, we're passing a pre-written function as the predicate. Copy the filter from
;; above, and replace `large-number?` with an anonymous function (i.e. Use `fn`) to
;; check for numbers less than 500.


;; EXERCISE: Use map and the functions you wrote earlier to convert the following sequence of Celcius temperatures to F: [0 21 40 100]


;; EXERCISE: Take the answer to the prior question (the call to map itself, not the output), and pass that in place of
;; the sequence to another call to a `map` that will convert them from F -> C. See that you get back the original numbers
;; (perhaps with some slight rounding errors)


;; EXERCISE: In the samples below: Finish the code so that the output is as shown:

(map (fn [card]) [{:card/rank 3} {:card/rank 10}])
;; Expected output: (3 10)

(keep (fn [card]) [{:card/rank 3} {:card/rank 6} {:card/rank 2} {:card/rank 10}])
;; expected output: (3 2)

;; There is a function called `comp`. It composes two functions together so that they act as one. This is just
;; like in math `f . g`, where you plug the value into `g`, then take that result and run it through `f`. For example:

(def cf (comp odd? inc))

;; NOTE that `comp` composes them such that the functions *run* from RIGHT to LEFT. In other words, the above
;; Returns a function that FIRST increments it's input, then passes that to `odd?` and returns the result. Try these:
(cf 1)
(cf 2)
(cf 3)

;; EXERCISE: Use `comp` to combine your temperature conversion functions `c-to-f` and `f-to-c` (uncomment the next line):
;; (def test-func (comp f-to-c c-to-f))
;; What do you expect this new function `test-func` to do? Try it out.


;; EXERCISE: Say you have `cf` as defined above. Use, `def`, `cf`, `comp`, and the `not` function to make a new function called
;; `cf2` that returns the exact opposite of `cf`.

;; In programming the VERB `map` (which we have in clojure as a function) means to apply some function to a range of
;; values to obtain a sequence of outputs. This is a lot like what you do in Algebra to graph an equation. You pick
;; x values like 1, 2, 3, etc., plug them into `f(x)`, and get out some `y` values. In Clojure (def y-values (map f [1 2 3 4 5]))
;; The NOUN `map` refers to the data structure that holds key-value pairs.  Perhaps you can see a parallel here? The action
;; of mapping an input to an output can be done with a continuous function (as in Algebra and out VERB map), but the data
;; NOUN `map` as a data structure is sort of like a memoized collection of known "pairs" (like x/y values)!

;; Thus, it makes total sense to treat the (the data structure) as a way to calculate results for a finite number of
;; questions. In other words instead of `f(x)` being math calculations, `f(x)` could just be a straight "dictionary lookup"

;; If you did all of the bonus things in the book, then you've already seen that maps can act like functions in Clojure.
;; they return the known value for that key, or nil if there is no such entry. For example, try running these:

(map {1  2
      10 5
      2  1} [1 2 3 8 9 10])

;; Vectors also act like functions, but treat the "input" as an index to look up:
(map [:a :b :c :d] [2 0 3])

;; QUESTION: What happens if you add 10 to the [2 0 3] vector in the example above?

;; Sets can act as a function, but they just return the item from the set, so they are not quite as interesting
(map #{:a :b :c} [:a :c :x :y])

;; There is an alternative function called `keep`. It is JUST like `map`, but it discards `nil`.

;; EXERCISE: Change `map` to `keep` in the examples above. Do you understand the results? Which of those seems
;; the most "useful", if any? Why?

