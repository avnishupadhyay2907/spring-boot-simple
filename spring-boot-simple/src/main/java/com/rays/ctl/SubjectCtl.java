package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.SubjectDTO;
import com.rays.form.SubjectForm;
import com.rays.service.SubjectService;

public class SubjectCtl extends BaseCtl {

	@Autowired
	public SubjectService subjectService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid SubjectForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		SubjectDTO dto = (SubjectDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			subjectService.update(dto);
			res.addData(dto.getId());
			res.addMessage("User Updated Successfully..!!");
		} else {
			long pk = subjectService.add(dto);
			res.addData(pk);
			res.addMessage("User added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		SubjectDTO dto = subjectService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			subjectService.delete(id);
		}

		res.addMessage("User deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody SubjectForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		SubjectDTO dto = (SubjectDTO) form.getDto();

		List list = subjectService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
}
