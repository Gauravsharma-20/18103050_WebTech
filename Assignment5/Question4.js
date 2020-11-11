var christmas_tree = (rows) =>{

	for(var i=0;i<rows;i++)
	{
		let tree = "";

		for(var j=0;j<rows-i;j++)
		{
			tree = tree + ' '; 
		}
		for(var k=1;k<=(2*i+1);k++)
		{
			if(i===0)
			{
				tree = tree + '*';
			}
			else
			{
				tree = tree + '0';
			}
		}
		console.log(tree);
	}

}

//input
christmas_tree(5);
christmas_tree(7);



//output
     *
    000
   00000
  0000000
 000000000

 
       *
      000
     00000
    0000000
   000000000
  00000000000 
 0000000000000
