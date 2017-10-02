/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   run_machine_run.c                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: gvan-roo <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2017/08/28 16:44:22 by gvan-roo          #+#    #+#             */
/*   Updated: 2017/09/27 07:58:58 by gvan-roo         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../headers/vm.h"

int				check_who_alive(t_champ *champ_head)
{
	t_champ		*champ_ptr;

	champ_ptr = champ_head;
	while (champ_ptr)
	{
		if (champ_ptr->alive)
			return (1);
		champ_ptr = champ_ptr->next;
	}
	return (0);
}

void			new_cycle_to_die(t_champ *champ_head, t_vm *vm)
{
	t_champ		*champ_ptr;

	champ_ptr = champ_head;
	while (champ_ptr)
	{
		if (!champ_ptr->called_alive)
			champ_ptr->alive = 0;
		champ_ptr->called_alive = 0;
		champ_ptr = champ_ptr->next;
	}
	if (vm->live_calls >= NBR_LIVE || vm->checks >= 10)
	{
		vm->cycle_to_die -= vm->cycle_delta;
		vm->checks = 0;
	}
	else
		vm->checks++;
	vm->live_calls = 0;
	vm->cur_cycle = 1;
}

void			call_live(t_champ *champ_head, t_champ *champ_ptr, t_vm *vm)
{
	int			p_num;
	int			ctr;

	ctr = 0;
	champ_ptr->pc++;
	champ_ptr->pc = mem_check(champ_ptr->pc);
	champ_ptr->exec_cycle += g_op_tab[0].no_cycles;
	p_num = vm->mem[mem_check(champ_ptr->pc)] * 256 * 256 * 256;
	p_num += vm->mem[mem_check(champ_ptr->pc + 1)] * 256 * 256;
	p_num += vm->mem[mem_check(champ_ptr->pc + 2)] * 256;
	p_num += vm->mem[mem_check(champ_ptr->pc + 3)];
	champ_ptr->pc = mem_check(champ_ptr->pc + 4);
	champ_ptr->called_alive = 1;
	champ_ptr = champ_head;
	while (champ_ptr)
	{
		if (champ_ptr->player_num == p_num)
		{
			print_ifno_nc(vm, champ_ptr);
			vm->last_live = champ_ptr->player_num;
			vm->live_calls++;
		}
		champ_ptr = champ_ptr->next;
	}
}

void			exec_champ(t_champ *champ_head, t_champ *champ_ptr, t_vm *vm)
{
	if (vm->mem[champ_ptr->pc] == 1)
		call_live(champ_head, champ_ptr, vm);
	else if (vm->mem[champ_ptr->pc] >= 2 && vm->mem[champ_ptr->pc] <= 16)
		vm->func[vm->mem[champ_ptr->pc]](vm, champ_ptr);
	else
		champ_ptr->exec_cycle++;
}

void			run_machine_run(t_champ *champ_head, t_vm *vm)
{
	t_champ		*champ_ptr;

	init_ncurses(vm->ncurses);
	while (check_who_alive(champ_head))
	{
		while (vm->cur_cycle < vm->cycle_to_die)
		{
			check_dump_cycle(champ_head, vm);
			champ_ptr = champ_head;
			while (champ_ptr)
			{
				if (champ_ptr->alive && !champ_ptr->exec_cycle)
					exec_champ(champ_head, champ_ptr, vm);
				if (champ_ptr->exec_cycle)
					champ_ptr->exec_cycle--;
				champ_ptr = champ_ptr->next;
			}
			if (vm->ncurses == 1)
				print_vm_nc(*vm, 64);
			vm->cur_cycle++;
			vm->total_cycles++;
		}
		new_cycle_to_die(champ_head, vm);
	}
}
