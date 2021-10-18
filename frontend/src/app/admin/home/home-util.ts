import {GenericTableColumn} from '../../shared/models/generic-table-column';
import {MessageUtil} from '../../utils/message-util';
import {GenericTableFilter} from '../../shared/models/generic-table-filter';

export class HomeUtil {
    static COLUMNS: GenericTableColumn[] = [
        { field: 'id', header: 'id' },
        { field: 'nome', header: 'Nome' },
        { field: 'email', header: 'Email' }
    ];

    static FILTERS: GenericTableFilter[] = [
        {
            field: 'nome',
            type: 'string',
            label: MessageUtil.LABELS_FILTER
        }
    ];
}
