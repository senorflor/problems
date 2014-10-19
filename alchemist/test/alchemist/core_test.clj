(ns alchemist.core-test
  (:require [clojure.test :refer :all]
            [alchemist.core :refer :all]))

(deftest given-example
  (testing "Example from problem description works"
    (is (= ["Silver" "Iron" "Copper"] (shortest-path path-ends)))))

(deftest invented-examples
  (testing "Artisinally hand-crafted example with path length of 2"
    (is (= ["Lead" "Gold"] (shortest-path ["Lead" "Gold"]))))
  (testing "Handlebar mustache 197.5° pour-over example with a path-length of 4"
    (is (= ["Lead" "Gold" "Nickel" "Copper"] (shortest-path ["Lead" "Copper"])))))
