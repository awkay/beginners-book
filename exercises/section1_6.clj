(ns section1_6
  "Making Things Happen."
  (:require [clojure.string :as str]))

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


;; EXERCISE: Write a function called `with-full-name`. It should accept a single argument called `person`, which is a map
;; where you expect there to be keys for `:person/first-name` and `:person/last-name`. Return the same `person` as you
;; receive, but use `assoc` and `str` to add a `:person/full-name` key with a value that is what you would expect.


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

;; If you did the BONUS things in the book, then you've already seen that maps (the data structure) can act like functions in Clojure.
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


;; There are two other utility functions in Clojure that can come in handy: `constantly` and `identity`.
;; The first takes a single value, and returns a function that always returns that value:

(def yes-function (constantly true))
(yes-function "hello")
(yes-function false)
(yes-function 42)

;; The other one is a function that just returns whatever it is passed:
(identity 42)
(identity "Hello")
(identity 8.9)

;; These come in handy when you are building more complex things, where functions can be "plugged in" to accomplish
;; some result. For example, let's say I gave you this function:

(defn calculate [custom-filter]
  (filter custom-filter (map inc [1 2 3 4 5])))

;; But you decided you just want everything the is calculated
(calculate (constantly true))

;; or a more complex function that let's you plug in post-processing:
(defn calculate2 [custom-filter post-process]
  (map post-process
    (filter custom-filter
      (map inc [1 2 3 4 5]))))

;; EXERCISE: What will the output of this be (guess before running):
(calculate2 odd? inc)

;; EXERCISE: What will the result of this be?
(calculate2 (constantly true) identity)


;; There is a built-in thing called Math which has a number of useful math functions. For example, to get the
;; square root of a number you would write:
(Math/sqrt 6)

;; and to round a number:
(Math/round 6.2)
(Math/round 6.8)

;; EXERCISE: Write a Clojure function that find the length of a hypotenuse of a right triangle: hypot(x, y) = sqroot ( x * x + y * y )


;; The following function will calculate the x coordinate (really we'd have +x and -x) for a circle, given a y coordinate
(defn circle-x [radius y]
  (when (<= (- radius) y radius)
    (let [x (Math/sqrt
              (-
                (* radius radius)
                (* y y)))]
      x)))

;; EXERCISE: Use `map` to get the x coordinates for a circle of radius 3, using the y values [-3 -2 -1 0 1 2 3]


;; The `doseq` function binds names to sequences (one element at a time). This is similar to map, but instead of
;; it taking a function and producing a new sequence, it expects you to do some "side effect" against the
;; real world with those values. For example, the `println` function prints a string to the "output":
(doseq [name ["Bob" "Tash" "Tony"]]
  (println "Hello" name))

;; We can use this along with our "circle calculator" to make a rough on-screen plot of a circle!
;; We'll do this by "shifting" the points of the circle to the right so that we can just use spaces and * to draw it.
;; We'll also "round" the numbers, since the columns of our screen are integers (0 1 2 3, etc).
(doseq [y [-3 -2 -1 0 1 2 3]]
  (let [radius       3
        x            (Math/round (circle-x radius y))       ; plus x
        neg-x        (- x)                                  ; The minus of x
        x-offset     10                                     ; Some space to shift the "plot" to the right
        ;; The `repeat` function returns a sequence of the same thing over and over...we'll use that to make
        ;; sequences of spaces. The `str/join` will join sequences of strings together as a single string
        ;; (str is an alias for clojure.string. See the require at the top of the file)
        left-padding (str/join (repeat (+ x-offset neg-x) " "))
        ;; How wide is the circle. Well, it is centered on the y axis, so we have -x to x, or 2x
        width        (* 2 x)
        ;; Make enough spaces to go between -x and x
        width-spaces (str/join (repeat width " "))]
    ;; Combine the bits together into an on-screen "line". We're leveraging the fact that the terminal
    ;; prints one line after enough, which is why we started at the "bottom" of the circle. Technically,
    ;; we're drawing it "upside down"...but it's a circle so who cares?
    (println (str left-padding "*" width-spaces "*"))))

;; EXERCISE: Try out the following:
(range -3 3)
(range -3 4)
(range -6 7)

;; EXERCISE: Change the code in the doseq above to use `range` instead of the literal [-3 -2 -1 0 1 2 3]

;; EXERCISE: Wrap the above `doseq` in a function called `draw-circle`, which takes TWO parameters:
;; the `x-offset`, and the desired `radius`. Remember to fix your range!
;; Test out your new `draw-circle` function to see that you can draw various
;; sized circles!


