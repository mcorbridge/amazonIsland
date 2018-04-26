Welcome to "Find the Amazon Island"

This short java app is in response to a question presented to me during a recent interview with Amazon.

During the interview, I was asked to write an algorithm to solve the following problem:

Consider a 5 x 5 grid:

<table>
<tbody>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>X</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
</tbody>
</table>

If  the '0' represents water and the 'X' represents land, you should conclude that there is ONE island.

Now consider these grids:

<table>
<tbody>
	<tr>
	<td>
		<table>
		<tbody>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>X</td>
		</tr>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		</tbody>
		</table>
	</td>
	<td>
	or
	</td>
	<td>
    		<table>
    		<tbody>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>X</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		</tbody>
    		</table>
    	</td>
	<tr>

</tbody>
</table>

Both these examples also show ONE island if you assume that the edge of the grid is an expanse of water.

Ya follow? How about this one:

<table>
<tbody>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>X</td>
<td>X</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>X</td>
<td>X</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
</tbody>
</table>

This represents ONE island as well.  Any 'X' that borders another 'X' horizontally or vertically (contiguous) combine to form a single island.

Now consider this grid:

<table>
<tbody>
<tr>
<td>X</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>X</td>
<td>0</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>X</td>
<td>0</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>X</td>
<td>0</td>
</tr>
<tr>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>X</td>
</tr>
</tbody>
</table>

This represents ONE island since the cells share common corners (Bingo!).  
(Although this example was not considered part of the original interview question, I decided to add this functionality to my algorithm.)


Have a look at the following examples of TWO islands in each grid:

<table>
<tbody>
	<tr>
	<td>
		<table>
		<tbody>
		<tr>
		<td>X</td>
		<td>X</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		<tr>
		<td>X</td>
		<td>X</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		</tr>
		<tr>
		<td>X</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>X</td>
		</tr>
		<tr>
		<td>X</td>
		<td>0</td>
		<td>0</td>
		<td>X</td>
		<td>0</td>
		</tr>
		<tr>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>X</td>
		</tr>
		</tbody>
		</table>
	</td>
	<td>
	and
	</td>
	<td>
    		<table>
    		<tbody>
    		<tr>
    		<td>X</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>X</td>
    		<td>X</td>
    		<td>0</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>0</td>
    		<td>0</td>
    		<td>0</td>
    		<td>X</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>X</td>
    		<td>X</td>
    		<td>0</td>
    		<td>X</td>
    		<td>0</td>
    		</tr>
    		<tr>
    		<td>X</td>
    		<td>X</td>
    		<td>0</td>
    		<td>0</td>
    		<td>X</td>
    		</tr>
    		</tbody>
    		</table>
    	</td>
	<tr>

</tbody>
</table>

Now go write the algorithm to find contiguous cells in any size grid.

Simple, eh?

Maybe ... I'm a just an organic chemist / environmental scientist / stone mason.   I have never taken a course in computer algorithms, and although I knew that recursion was part of the solution, I certainly didn't know what the final algorithm might be, nor was I about to discover it in the 15 minutes remaining in the interview.

Oh, and to make matters worse you are supposed solve this real time in front of the interviewer using some web based text editor that makes Notepad look like Intellij?!

But I wasn't going to go down without a fight.  So after the interview I poured a cup of coffee, sparked up Intellij and wrote an algorithm in Java that solves the challenge.

Have a look, and I hope your Amazon interview goes better than mine!

You can see this app running online  at: https://www.codiva.io/p/fef9772c-e319-4121-8590-4b8dc14f2d6b

Click on the 'FindIsland.java' file, then hit RUN.  I don't know why it prompts with 'There are compilation errors', but just click OK and then run again. Apologies for the size of the output panel.  I didn't write the UI.




