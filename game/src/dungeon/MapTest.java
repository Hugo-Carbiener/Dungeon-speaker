package dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class MapTest {
	
	Map testMap;
	
	@Before 
	public void setup() throws Exception {
		testMap = new Map(4,4,0.5,1);
		while (testMap.getRoomNumber() < 2){
			testMap = new Map(4,4,0.5,1);
		}
		testMap.displayFromRoom(testMap.getStartingRoom());	
	}
	
	@Test
	public void getRoomTest() {
		Room gotRoom = testMap.getRoom("00", testMap.getStartingRoom());
		assertEquals("00", gotRoom.getId());
		assertEquals(testMap.getStartingRoom(), gotRoom.getPreviousRoom());
	
		//Test on root
		gotRoom = testMap.getRoom("0", testMap.getStartingRoom());
		assertSame(testMap.getStartingRoom(), gotRoom);
		
		//Test on a room which is n-th child with a n-1 limit
		gotRoom = testMap.getRoom("005", testMap.getStartingRoom());
		assertNull(gotRoom);
				
		//Test on a room too "deep" (room level too high) to exist 
		gotRoom = testMap.getRoom("00000000", testMap.getStartingRoom());
		assertNull(gotRoom);
		
		//Test on a room the id of which does not correspond to the norm
		gotRoom = testMap.getRoom("aled", testMap.getStartingRoom());
		assertNull(gotRoom);
	}
}