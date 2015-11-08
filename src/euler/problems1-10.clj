(ns euler.problems1-10
  (require [clojure.string :as s]))


(defn problem1 [] 
  (->> (range 1000)
       (filter #(or (= 0 (mod % 3)) (= 0 (mod % 5))))
       (reduce +)))

(def fib-seq
  (lazy-cat [1 2] (map + fib-seq (rest fib-seq))))

(defn- fib
  [limit]
  (reduce + (take-while (partial >= limit)
                        (filter even? fib-seq))))

(defn problem2 []
  (fib 4000000))

(defn- factors
  ([num]
   (factors num 2 '()))
  ([num toCheck lst]
   (cond
    (> toCheck num) lst
    (= 0 (mod num toCheck)) (recur (/ num toCheck) toCheck (cons toCheck lst))
    :else (recur num (inc toCheck) lst))))

(defn problem3 []
  (apply max (factors 600851475143)))

(defn- isPalindrome? [num]
  (= (str num) (s/reverse (str num))))

(defn problem4 []
  (->> (for [i (range 100 1000) j (range 100 1000)] (* i j))
       (filter isPalindrome?)
       (apply max)))

(defn- gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn- lcm [a b]
  (/ (* a b) (gcd a b)))

(defn problem5 []
  (reduce lcm (range 1 21)))

(def rng (range 1 101))

(defn- sum-squares [lst]
  (reduce + (map #(* % %) lst)))

(defn- square-sum [lst]
  (let [x (reduce + lst)]
    (* x x)))

(defn problem6 []
  (- (square-sum rng) (sum-squares rng)))

(defn- is-prime? [num]
  (cond
      (< num 2) false
      (= 0 (mod num 2)) (= 2 num)
      :else (loop [root (Math/sqrt num) x 3]
              (cond
               (> x root) true
               (= 0 (mod num x)) false
               :else (recur root (+ 2 x))))))

(defn problem7 []
  (->> (iterate inc 1)
       (filter is-prime?)
       (drop 10000)
       (first)))
