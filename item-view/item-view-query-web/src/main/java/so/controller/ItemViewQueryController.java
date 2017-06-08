package so.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.so.service.ItemViewService;
import com.so.view.ItemView;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@RestController
public class ItemViewQueryController {

	private ItemViewService itemViewService;

	@Autowired
	public ItemViewQueryController(ItemViewService itemViewService) {
		this.itemViewService = itemViewService;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<List<ItemView>> findAllItems() {
		return new ResponseEntity<>(itemViewService.findAllItems(), HttpStatus.OK);
	}
}
