val inventory_1: 
    Map[Int,(String,Int,Double)] = Map(
        100->("Mari",50,100.00),
        101->("Chocalate Mari",30,120.00),
        102->("Nice",40,130.00),
        103->("Wepas",35,142.50),
        104->("Cheese Craker",50,175.00)
    )

val inventory_2:
     Map[Int,(String,Int,Double)] = Map(
        102->("Nice",50,135.00),
        103->("Wepas",40,142.50),
        105->("Tikiri Mari",10,90.00)
)

def printInventory(inventory:Map[Int,(String,Int,Double)]): Unit={
    println("\n")
    println("-----Products of this inventory------")
    var i = 1
    inventory.values.map(_._1).foreach{     // get the first value from key-value the inventary map and 
        a => println(s"$i.$a")
        i = i+1
    }
}

def totalValue(inventory:Map[Int,(String,Int,Double)]): Unit={
    val wholeValue = inventory.values.map{            // List
        case (_, quantity, price) => quantity*price
    }              

    var inventoryValue: Double = 0;
    wholeValue.foreach{
        a => inventoryValue = inventoryValue + a
    }

    println("\n");
    println(s"Total value of this inventory in each product is : $wholeValue");
    println(s"Total value of inventory is : Rs.$inventoryValue")
}

def checkEmpty(inventory:Map[Int,(String,Int,Double)]): Unit={
    if(inventory.isEmpty){
        println("\nInventory is Empty.")
    }else{
        println("\nInventory is not Empty.")
    }
}

def mergeInventory(inventory1:Map[Int,(String,Int,Double)],inventory2:Map[Int,(String,Int,Double)]): Unit={
    val newInventory = (inventory1.keySet ++ inventory2.keySet).map{
        key =>
            val prod1 = inventory1.getOrElse(key,("",0,0.0))
            val prod2 = inventory2.getOrElse(key,("",0,0.0))

            val name = if(prod1._1.nonEmpty) prod1._1 else prod2._1
            val quantity = prod1._2 +  prod2._2
            val price = Math.max(prod1._3,prod2._3)

            key -> (name,quantity,price)
        }.toMap

        println("\n")
        newInventory.foreach{
            case(id,(name,quantity,price)) => 
                println(s"$id => name:$name , quantity:$quantity , price: Rs.$price")
            }
    }

def productFind(id:Int): Unit={
    inventory_1.get(id) match{
        case Some((name,quantity,price)) =>
            println(s"\nFound product with id : $id !")
            println(s"$id => name:$name , quantity:$quantity , price: Rs.$price\n")
        case None => 
            println(s"\nThere is no product with this product id : $id in this inventory.\n")
        }
}

def main(args: Array[String]): Unit={
    printInventory(inventory_1)
    totalValue(inventory_1)
    checkEmpty(inventory_1)
    mergeInventory(inventory_1,inventory_2)
    productFind(102)
}