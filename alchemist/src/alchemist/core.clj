(ns alchemist.core
  (:require [clojure.set :as s]
            [clojure.math.combinatorics :as c]))

;;;;; Dirty competition style, e.g. closing path function over
;;;;; hardcoded state, etc.

(def elements ["Lead" "Gold" "Silver" "Iron" "Copper" "Nickel"])

(def i->e
  (as-> (range 6) _
        (interleave _ elements)
        (apply hash-map _)))

(def e->i
  (s/map-invert i->e))

(def graph
  [[0 1 1 0 0 0]
   [1 0 1 0 0 1]
   [1 1 0 1 0 0]
   [0 0 1 0 1 0]
   [0 0 0 1 0 1]
   [0 1 0 0 1 0]])

(def path-ends
  ["Silver" "Copper"])

(defn transmutable
  [[e1 e2]]
  (let [i1 (e->i e1)
        i2 (e->i e2)
        adjacents (graph i1)]
    (= 1 (adjacents i2))))

(defn is-valid-path?
  [graph path]
  (->> (partition 2 1 path)
       (every? transmutable)))

(defn shortest-path
  [[start end]]
  (->> (c/subsets elements)
       (mapcat c/permutations)
       (filter #(= (first %) start))
       (filter #(= (last %) end))
       (filter (partial is-valid-path? graph))
       first))
