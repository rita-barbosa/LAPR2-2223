# US 019 - To divide the set of all stores into two subsets

# 4. Tests 

> Yet to be developed.

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class SubDivideAgenciesController

```java
 private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public List<String> getAgenciesPartitions(){
        Partition p = new Partition(agencyRepository.getDealsNumberOfAgencies());
        return p.getSubLists();
    }
```
## Class Partition

```java
private void calculatePartitions(List<Integer> listOfDeals) {
        int listOfDealsSize = listOfDeals.size();
        int size = getPowerOfTwo(listOfDealsSize); 

        this.minDifference = Integer.MAX_VALUE; 

        for (int i = 0; i < size; i++) { 
            List<Integer> subList1 = new ArrayList<>(listOfDeals); 
            List<Integer> subList2 = new ArrayList<>(listOfDeals); 
            int subListSum1 = 0;
            int subListSum2 = 0; 

            String binary = decimalToBinary(i);

            for (int j = 0; j < binary.length(); j++) { 
                if (binary.charAt(j) == '0') { 
                    subList1.set(j, 0);
                    subListSum2 += listOfDeals.get(j); 
                } else {
                    subList2.set(j, 0);
                    subListSum1 += listOfDeals.get(j);
                }
            }

            if (binary.length() < size) { 
                for (int k = binary.length(); k < listOfDealsSize; k++) { 
                    subList1.set(k, 0); 
                    subListSum2 += listOfDeals.get(k); 
                }
            }

            int difference = calculateDifference(subListSum1, subListSum2); 
            if (this.minDifference > difference) { 
                this.minDifference = difference;
                this.subList1 = subList1; 
                this.subList2 = subList2; 
            }
        }
    }
```

# 6. Integration and Demo 

> None.

# 7. Observations

> For a listOfDeals size input bigger than 21, the program takes a few minutes to calculate the partitions.





