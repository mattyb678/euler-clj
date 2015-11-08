(ns euler.problems1-10)


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
