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

(def long_num 7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450)

(defn- to-digit-seq [x]
  (map #(Character/getNumericValue %) (str x)))

(defn problem8 []
  (apply max (map #(apply * %) (partition 13 1 (to-digit-seq long_num)))))

(defn problem9 []
  (first (for [a (range 1 334)
               b (range (+ 1 a) (- 1001 a))
               :let [c (- 1000 a b)]
               :when (= (* c c) (+ (* a a) (* b b)))]
           (* a b c))))

(defn problem10 []
  (->> (range 1 2000001)
       (filter #(is-prime? %))
       (reduce +)))

(defn- gen-primes []
  (letfn [(reinsert [x table prime]
            (update-in table [(+ x prime)] conj prime))
          (sieve [x table]
            (if-let [factors (get table x)]
              (recur (inc x) (reduce #(reinsert x %1 %2) (dissoc table x) factors))
              (lazy-seq (cons x (sieve (inc x) (assoc table (* x x) (list x)))))))]
    (sieve 2 {})))

(defn problem10 []
  (->> (gen-primes)
       (filter #(< % 2000000))
       (reduce +)))
