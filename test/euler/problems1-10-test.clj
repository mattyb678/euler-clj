(ns euler.problems1-10-test
  (:require [clojure.test :refer :all]
            [euler.problems1-10 :refer :all]))

(deftest problem1-test
  (testing "Problem 1"
    (is (= 233168 (problem1)))))

(deftest problem2-test
  (testing "Problem 2"
    (is (= 4613732 (problem2)))))
