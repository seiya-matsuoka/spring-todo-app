package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.entity.ToDo;
import com.example.todo.form.ToDoForm;
import com.example.todo.helper.ToDoHelper;
import com.example.todo.service.ToDoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("todos", toDoService.findAllToDo());
        return "todo/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model,
            RedirectAttributes attributes) {

    	ToDo ToDo = toDoService.findByIdToDo(id);
        if (ToDo != null) {
            model.addAttribute("todo", toDoService.findByIdToDo(id));
            return "todo/detail";
        } else {
            attributes.addFlashAttribute("errorMessage", "対象データがありません");
            return "redirect:/todos";
        }
    }

    @GetMapping("/form")
    public String newToDo(@ModelAttribute ToDoForm form) {
        form.setIsNew(true);
        return "todo/form";
    }

    @PostMapping("/save")
    public String create(@Validated ToDoForm form, 
    		BindingResult bindingResult, 
            	RedirectAttributes attributes) {

    	if (bindingResult.hasErrors()) {
			form.setIsNew(true);
			return "todo/form";
		}

        ToDo ToDo = ToDoHelper.convertToDo(form);
        toDoService.insertToDo(ToDo);
        attributes.addFlashAttribute("message", "新しいToDoが作成されました");
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model,
            RedirectAttributes attributes) {
        ToDo target = toDoService.findByIdToDo(id);
        if (target != null) {
            ToDoForm form = ToDoHelper.convertToDoForm(target);
            model.addAttribute("toDoForm", form);
            return "todo/form";            
        } else {
            attributes.addFlashAttribute("errorMessage", "対象データがありません");
            return "redirect:/todos";            
        }
    }

    @PostMapping("/update")
    public String update(@Validated ToDoForm form, 
    		BindingResult bindingResult, 
            	RedirectAttributes attributes) {

    	if (bindingResult.hasErrors()) {
			form.setIsNew(false);
			return "todo/form";
		}

    	ToDo ToDo = ToDoHelper.convertToDo(form);
        toDoService.updateToDo(ToDo);
        attributes.addFlashAttribute("message", "ToDoが更新されました");
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
    	toDoService.deleteToDo(id);
        attributes.addFlashAttribute("message", "ToDoが削除されました");
        return "redirect:/todos";
    }
}
