(ns simplediff.core-test
  (:require [clojure.test :refer :all]
            [simplediff.core :refer :all]))

(require ['simplediff.diff :as 'diff])


(deftest exists-lines-works
  (testing "exists-lines works")
  (is (= true (diff/exists-line? "hello" ["some line" "hello"]))))

(deftest exists-lines-works-again
  (testing "exists-lines works")
  (is (= false (diff/exists-line? "hello" ["some line" "other lines"]))))

(deftest simple-compare1
  (testing "simple compare1 works")
  (is (= (diff/docompare [ "this is a line" ] 0 [] 0 )
         (list (list 1 "d" 0"< this is a line"))
         )))

(deftest simple-compare2
  (testing "simple compare2 works")
  (is (= (diff/docompare [] 0 [ "this is a line" ] 0 )
         (list (list 0 "a" 1 "> this is a line"))
         )))

(deftest simple-compare3
  (testing "simple compare3 works")
  (is (= (diff/docompare ["this is a new line"] 0 [ "this is a line" ] 0 )
         (list (list 1 "c" 1 "< this is a new line" "> this is a line")))))

(deftest complex-compare1
  (testing "complex compare1 works")
  (is (= (diff/docompare ["this is a line"] 0 [ "an added line" "a second added line" "this is a line" ] 0 )
         '((0 "a" 1 "> an added line")
           (0 "a" 2 "> a second added line")))))

(deftest complex-compare2
  (testing "complex compare2 works")
  (is (= (diff/docompare ["this is a line" "an line"] 0 [ "an line" "a second added line" "this is a line" ] 0 )
         '((1 "d" 0 "< this is a line")
           (2 "a" 2 "> a second added line")
           (2 "a" 3 "> this is a line")))))

(deftest complex-compare3
  (testing "complex compare3 works")
  (is (= (diff/docompare [ "an line" "a second added line" "this is a line" ] 0 ["this is a line" "an line"] 0)
         '((1 "d" 0 "< an line")
           (2 "d" 0 "< a second added line")
           (3 "a" 2 "> an line")))))


