(ns section1_5
  "Nesting Data."
  (:require
    [clojure.set :as set]))

;; Nesting data is pretty easy. Just use data structures anywhere you would normally put any kind of value.
;; Clojure is not sensitive to extra space or lines, so it's usually nice to format these for readability.

(def m {:person/name    "Bonnie"
        :person/age     42
        :person/manager {:person/name     "Rey"
                         :person/age      33
                         :person/children [{:person/name "Bobby"}
                                           {:person/name "Allison"}
                                           {:person/name "Brie"}]
                         :person/manager  {:person/name "Polly"
                                           :person/age  68}}})

;; The function get-in is capable of traversing both maps and vectors, as long as you remember to use an offset
;; when you reach the vector
(get-in m [:person/manager :person/children 1])

;; EXERCISE: Get Polly's age


;; You can place things at a path with assoc-in, including to some extend vector offsets:
(assoc-in m [:person/manager :person/children 1 :person/name] "Allie")

;; EXERCISE: what if you change the number 1 above to 6?

;; But assoc-in will never CREATE vectors for you, even if you use numbers as keys
;; but instead creates nested maps:
(assoc-in m [:person/likes 1] "pizza")

;; If you need to put a vector (or set) somewhere, you have to do it more like this (assoc or assoc-in):
(assoc m :person/likes ["pizza"])
(assoc m :person/likes #{"pizza"})
(assoc-in m [:person/manager :person/likes] ["pizza"])

;; We didn't mention this yet, but maps can easily be combined with merge:
(merge {:a 1} {:b 2} {:c 3})

;; but merge isn't "deep". It merges right-to-left, and OVERWRITES top-level keys:
(merge {:a {:b 1}} {:a {:c 2} :x 1})

;; QUESTION: Did you expect something different from the prior merge? What did you expect?

;; There is a way to get "deep merge", but it isn't included in the core functions of Clojure, and is
;; too advanced for this point in our learning.
