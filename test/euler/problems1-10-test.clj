(ns euler.problems1-10-test
  (:require [clojure.test :refer :all]
            [euler.problems1-10 :refer :all]))

(deftest problem1-test
  (testing "Problem 1"
    (is (= 233168 (problem1)))))

(deftest problem2-test
  (testing "Problem 2"
    (is (= 4613732 (problem2)))))

(deftest problem3-test
  (testing "Problem 3"
    (is (= 6857 (problem3)))))

(deftest problem4-test
  (testing "Problem 4"
    (is (= 906609 (problem4)))))
