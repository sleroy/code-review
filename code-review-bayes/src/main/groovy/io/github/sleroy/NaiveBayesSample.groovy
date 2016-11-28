package io.github.sleroy

import io.github.sleroy.ml.classifier.knn.NearestNeighbors
import io.github.sleroy.ml.classifier.votes.MajorityVoteResponse
import io.github.sleroy.ml.dataset.MapClasses
import io.github.sleroy.ml.dataset.MemoryNormalizedDataset
import io.github.sleroy.ml.dataset.NormalizedRecord;
import io.github.sleroy.ml.distance.EuclidianRecordDistance
import io.github.sleroy.ml.distance.ManhattanRecordDistance

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.util.Pair;

class NaiveBayesSample {

	static main(args) {

		def classes = new MapClasses(["male": 0, "female" :1]);

		def dataset = new MemoryNormalizedDataset(classes);


		def record1 = dataset.addRecord('male', [6 , 180, 12]);
		def record2 = dataset.addRecord('male', [5.92 , 190, 11]);
		def record3 = dataset.addRecord('male', [5.58 , 170, 12]);
		def record4 = dataset.addRecord('male', [5.92 , 165, 10]);
		def record5 = dataset.addRecord('female', [5 , 100, 6]);
		def record6 = dataset.addRecord('female', [5.5 , 150, 8]);
		def record7 = dataset.addRecord('female', [5.42 , 130, 7]);
		def record8 = dataset.addRecord('female', [5.75 , 150, 9]);


		println "\n#\n# DATASET \n#"

		dataset.each ({ it ->
			println "Record " + it.attributes();
		});



		println "\n#\n# SIMILARY\n#"

		println '\n== Example computing manhattan distance from record #1\n'
		def distanceAlgo = new ManhattanRecordDistance();
		dataset.each({
			println("Distance between two records " + distanceAlgo.distance(record1, it));
		});

		println '\n== Example computing euclidian distance from record #1\n'
		def distanceAlgo2 = new EuclidianRecordDistance();
		dataset.each({
			println("Distance between two records " + distanceAlgo2.distance(record1, it));
		});


		println "\n#\n# Neighbours\n#"


		def neighbors = new NearestNeighbors();
		def femaleRecord = dataset.newRecord("female", 6, 130, 8)
		println "Who is the closest of " + femaleRecord.attributes()
		def closestNeighbors = neighbors.compute(dataset, femaleRecord, distanceAlgo, 6);

		println "Closest neighbors $closestNeighbors"

		def majorityVoteResponse = new MajorityVoteResponse();
		//List<Pair<NormalizedRecord, Double>>
		def classe = majorityVoteResponse.vote(closestNeighbors, classes);

		println "Voted classe : $classe"

		println "Classes $classes"



		println '\n== Building classifier from dataset\n'



		DescriptiveStatistics stats = new DescriptiveStatistics();
		dataset.each({stats.addValue(it.attributes()[0])}); // add height

		println 'Height mean ' + stats.mean;
	}
}

